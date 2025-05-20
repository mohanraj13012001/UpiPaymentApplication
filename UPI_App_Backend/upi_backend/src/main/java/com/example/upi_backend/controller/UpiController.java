package com.example.upi_backend.controller;

import com.example.upi_backend.dto.*;
import com.example.upi_backend.model.User;
import com.example.upi_backend.service.UpiService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller to manage UPI user operations such as registration,
 * balance check, money addition, UPI status toggle, user retrieval,
 * and login.
 */
@Validated
@RestController
@RequestMapping("api/v1/upi")
@CrossOrigin(origins = "*")
public class UpiController {

    private static final Logger logger = LoggerFactory.getLogger(UpiController.class);

    private final UpiService upiService;

    public UpiController(UpiService upiService){
        this.upiService = upiService;
    }

    /**
     * Registers a new user with the provided user details.
     *
     * @param userDto the user data transfer object containing registration info
     * @return ResponseEntity with created user response and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<UpiResponseDto> registerUser(@RequestBody @Valid UserDto userDto){
        logger.info("Registering user with phone number: {}", userDto.getPhoneNumber());
        UpiResponseDto userResponseDto = upiService.createUser(userDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    /**
     * Retrieves account balance for a user identified by phone number and secret PIN.
     *
     * @param phoneNumber user's phone number
     * @param secretPin   user's secret PIN for authentication
     * @return ResponseEntity with balance response and HTTP 200 status
     */
    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDto> getAccountBalance(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam(value = "secretPin", required = true) String secretPin) {

        logger.info("Fetching balance for phone number: {}", phoneNumber);
        BalanceResponseDto balanceResponseDto = upiService.getAccountBalance(phoneNumber, secretPin);
        return new ResponseEntity<>(balanceResponseDto, HttpStatus.OK);
    }

    /**
     * Adds money to the user's account.
     *
     * @param request the add money request DTO containing phone number and amount
     * @return ResponseEntity with add money response and HTTP 200 status
     */
    @PostMapping("/add-money")
    public ResponseEntity<AddMoneyResponseDto> addMoney(@RequestBody AddMoneyRequestDto request) {
        logger.info("Adding money for phone number: {}, amount: {}", request.getPhoneNumber(), request.getAmount());
        AddMoneyResponseDto response = upiService.addMoney(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Toggles the UPI status (enable/disable) for a user.
     *
     * @param request the UPI toggle request DTO containing phone number and desired status
     * @return ResponseEntity with toggle response and HTTP 200 status
     */
    @PostMapping("/toggle")
    public ResponseEntity<UpiToggleResponseDto> toggleUpi(@Valid @RequestBody UpiToggleRequestDto request) {
        logger.info("Toggling UPI status for phone number: {}, enable: {}", request.getPhoneNumber(), request.isUpiEnabled());
        UpiToggleResponseDto response = upiService.toggleUpiStatus(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves user details by phone number.
     *
     * @param phoneNumber the user's phone number
     * @return ResponseEntity with user details and HTTP 200 status
     */
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<User> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        logger.info("Fetching user details for phone number: {}", phoneNumber);
        User user = upiService.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(user);
    }

    /**
     * Authenticates user login with given credentials.
     *
     * @param dto the login request DTO containing phone number and secret PIN
     * @return ResponseEntity with login response and HTTP 200 status
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        logger.info("User login attempt for phone number: {}", dto.getPhoneNumber());
        LoginResponseDto response = upiService.login(dto);
        return ResponseEntity.ok(response);
    }
}
