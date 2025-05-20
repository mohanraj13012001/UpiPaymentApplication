package com.example.upi_backend.service;

import com.example.upi_backend.dto.*;
import com.example.upi_backend.exception.InvalidAmountException;
import com.example.upi_backend.exception.MaxBalanceExceededException;
import com.example.upi_backend.exception.UpiDisabledException;
import com.example.upi_backend.exception.UserNotFoundException;
import com.example.upi_backend.model.User;
import com.example.upi_backend.repositary.UpiRepositary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UpiService {

    private static final Logger logger = LoggerFactory.getLogger(UpiService.class);

    private final UpiRepositary upiRepositary;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UpiService(UpiRepositary upiRepositary, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.upiRepositary = upiRepositary;
        this.mapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with encrypted PIN.
     *
     * @param userDto user details to register
     * @return response with user ID and success message
     */
    public UpiResponseDto createUser(UserDto userDto) {
        logger.info("Registering new user with phone number: {}", userDto.getPhoneNumber());
        User user = mapper.map(userDto, User.class);
        String encryptedPin = passwordEncoder.encode(userDto.getSecretPin());
        user.setSecretPin(encryptedPin);

        UserDto savedUser = mapper.map(upiRepositary.save(user), UserDto.class);
        return new UpiResponseDto(savedUser.getId(), "User Registered Successfully");
    }

    /**
     * Retrieves account balance for a user.
     *
     * @param phoneNumber user's phone number
     * @param secretPin   user's secret PIN
     * @return balance response DTO
     */
    public BalanceResponseDto getAccountBalance(String phoneNumber, String secretPin) {
        logger.info("Fetching account balance for phone number: {}", phoneNumber);
        if (phoneNumber == null || secretPin == null) {
            throw new IllegalArgumentException("Phone number and secret PIN must not be null");
        }

        User user = upiRepositary.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found with this phone number"));

        if (!passwordEncoder.matches(secretPin.trim(), user.getSecretPin())) {
            throw new IllegalArgumentException("Invalid secret PIN");
        }

        BalanceResponseDto balanceResponseDto = new BalanceResponseDto();
        balanceResponseDto.setPhoneNumber(user.getPhoneNumber());
        balanceResponseDto.setBalance(user.getBalance());
        balanceResponseDto.setMessage("Account balance fetched successfully");

        return balanceResponseDto;
    }

    /**
     * Adds money to the user's UPI account.
     *
     * @param request contains phone number, PIN, and amount
     * @return response with updated balance
     */
    public AddMoneyResponseDto addMoney(AddMoneyRequestDto request) {
        logger.info("Adding money for phone number: {}", request.getPhoneNumber());
        User user = upiRepositary.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getSecretPin().trim(), user.getSecretPin())) {
            throw new IllegalArgumentException("Invalid secret PIN");
        }

        if (!user.isUpiEnabled()) {
            throw new UpiDisabledException("UPI is not enabled");
        }

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        BigDecimal newBalance = user.getBalance().add(request.getAmount());

        if (newBalance.compareTo(BigDecimal.valueOf(100000)) > 0) {
            throw new MaxBalanceExceededException("Max balance limit is ₹1,00,000");
        }

        user.setBalance(newBalance);
        upiRepositary.save(user);

        return new AddMoneyResponseDto(user.getPhoneNumber(), user.getBalance(), "Money added successfully");
    }

    /**
     * Toggles the UPI status (enabled/disabled) for the user.
     *
     * @param request contains phone number and new UPI status
     * @return response indicating UPI status change
     */
    public UpiToggleResponseDto toggleUpiStatus(UpiToggleRequestDto request) {
        logger.info("Toggling UPI status for phone number: {}", request.getPhoneNumber());
        User user = upiRepositary.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUpiEnabled(request.isUpiEnabled());
        upiRepositary.save(user);

        String status = request.isUpiEnabled() ? "enabled" : "disabled";
        return new UpiToggleResponseDto(user.getPhoneNumber(), user.isUpiEnabled(), "UPI " + status + " successfully");
    }

    /**
     * Retrieves user details by phone number without the secret PIN.
     *
     * @param phoneNumber user's phone number
     * @return user object without PIN
     */
    public User getUserByPhoneNumber(String phoneNumber) {
        logger.info("Fetching user details for phone number: {}", phoneNumber);
        User user = upiRepositary.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found with phone number: " + phoneNumber));

        user.setSecretPin("");
        return user;
    }

    /**
     * Authenticates user login with phone number and PIN.
     *
     * @param dto login request containing phone number and PIN
     * @return response with user details if authenticated
     */
    public LoginResponseDto login(LoginRequestDto dto) {
        logger.info("User attempting to login with phone number: {}", dto.getPhoneNumber());
        Optional<User> userOpt = upiRepositary.findByPhoneNumber(dto.getPhoneNumber());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with this phone number");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(dto.getSecretPin(), user.getSecretPin())) {
            throw new IllegalArgumentException("Invalid secret PIN");
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUpiEnabled(user.isUpiEnabled());
        userDto.setBalance(user.getBalance());

        LoginResponseDto response = new LoginResponseDto();
        response.setMessage("Login successful");
        response.setUserDto(userDto);

        return response;
    }


}
