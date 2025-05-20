package com.example.upi_backend.service;

import com.example.upi_backend.dto.TransactionReponseDto;
import com.example.upi_backend.dto.TransactionRequestDto;
import com.example.upi_backend.dto.UserDto;
import com.example.upi_backend.model.PaymentTransfer;
import com.example.upi_backend.model.User;
import com.example.upi_backend.repositary.PaymentTransferRepository;
import com.example.upi_backend.repositary.UpiRepositary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final UpiRepositary upiRepositary;
    private final PaymentTransferRepository paymentTransferRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TransactionService(UpiRepositary upiRepositary, PaymentTransferRepository paymentTransferRepository) {
        this.upiRepositary = upiRepositary;
        this.paymentTransferRepository = paymentTransferRepository;
    }

    /**
     * Transfers the specified amount from one user to another if all validation checks pass.
     *
     * @param dto the transaction request DTO containing details like sender, receiver, amount, and secret pin.
     * @throws IllegalArgumentException if any validation fails such as invalid PIN, same user transfer, UPI not enabled,
     *                                  insufficient balance, max limits exceeded (per transaction or daily).
     */
    @Transactional
    public void transferAmount(TransactionRequestDto dto) {
        logger.info("Initiating transfer from User ID {} to User ID {}", dto.getFromUserId(), dto.getToUserId());

        Optional<User> user = upiRepositary.findById(dto.getFromUserId());
        if (user.isEmpty()) {
            logger.error("User not found with ID: {}", dto.getFromUserId());
            throw new IllegalArgumentException("User not found.");
        }

        String rawPin = dto.getSecretPin();
        String storedEncodedPin = user.get().getSecretPin();

        if (!passwordEncoder.matches(rawPin, storedEncodedPin)) {
            logger.warn("Invalid secret PIN for User ID {}", dto.getFromUserId());
            throw new IllegalArgumentException("Invalid secret PIN");
        }

        if (dto.getFromUserId().equals(dto.getToUserId())) {
            logger.warn("Sender and receiver are the same user ID: {}", dto.getFromUserId());
            throw new IllegalArgumentException("Cannot transfer to the same user.");
        }

        User sender = upiRepositary.findById(dto.getFromUserId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        User receiver = upiRepositary.findById(dto.getToUserId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        if (!sender.isUpiEnabled() || !receiver.isUpiEnabled()) {
            logger.warn("UPI not enabled for sender or receiver.");
            throw new IllegalArgumentException("Both users must have UPI enabled.");
        }

        BigDecimal amount = dto.getAmount();
        if (sender.getBalance().compareTo(amount) < 0) {
            logger.warn("Insufficient balance for User ID {}", sender.getId());
            throw new IllegalArgumentException("Insufficient balance.");
        }

        BigDecimal receiverNewBalance = receiver.getBalance().add(amount);
        if (receiverNewBalance.compareTo(BigDecimal.valueOf(100000)) > 0) {
            logger.warn("Receiver balance limit exceeded for User ID {}", receiver.getId());
            throw new IllegalArgumentException("Receiver's balance exceeds ₹1,00,000 limit.");
        }

        LocalDate today = LocalDate.now();
        int transfersToday = paymentTransferRepository.countByFromUserIdAndDateTimeBetween(
                sender.getId(),
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );

        if (transfersToday >= 3) {
            logger.warn("Daily transfer count limit reached for User ID {}", sender.getId());
            throw new IllegalArgumentException("Maximum of 3 transfers allowed per day.");
        }

        if (amount.compareTo(BigDecimal.valueOf(20000)) > 0) {
            logger.warn("Per transaction limit exceeded: {}", amount);
            throw new IllegalArgumentException("Maximum transfer amount per transaction is ₹20,000");
        }

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        BigDecimal totalTransferredToday = paymentTransferRepository
                .findTotalAmountTransferredToday(dto.getFromUserId(), startOfDay, endOfDay);

        BigDecimal newTotal = totalTransferredToday.add(amount);
        if (newTotal.compareTo(BigDecimal.valueOf(50000)) > 0) {
            logger.warn("Daily transfer limit exceeded for User ID {}", sender.getId());
            throw new IllegalArgumentException("Daily transfer limit exceeded. Max ₹50,000 allowed per day.");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiverNewBalance);

        upiRepositary.save(sender);
        upiRepositary.save(receiver);

        PaymentTransfer transfer = new PaymentTransfer();
        transfer.setAmount(amount);
        transfer.setDateTime(LocalDateTime.now());
        transfer.setFromUser(sender);
        transfer.setToUser(receiver);

        paymentTransferRepository.save(transfer);

        logger.info("Transfer successful. From User ID {} to User ID {}, Amount: {}", sender.getId(), receiver.getId(), amount);
    }

    public List<TransactionReponseDto> getAllTransactions() {
        logger.info("Fetching all transactions...");
        List<PaymentTransfer> paymentTransferList = paymentTransferRepository.findAll();

        return paymentTransferList.stream()
                .map(transfer -> new TransactionReponseDto(
                        transfer.getId(),
                        transfer.getAmount(),
                        transfer.getDateTime(),
                        new UserDto(
                                transfer.getFromUser().getId(),
                                transfer.getFromUser().getPhoneNumber(),
                                transfer.getFromUser().getUsername(),
                                transfer.getFromUser().isUpiEnabled(),
                                transfer.getFromUser().getBalance(),
                                "",
                                null, null
                        ),
                        new UserDto(
                                transfer.getToUser().getId(),
                                transfer.getToUser().getPhoneNumber(),
                                transfer.getToUser().getUsername(),
                                transfer.getToUser().isUpiEnabled(),
                                transfer.getToUser().getBalance(),
                                "",
                                null, null
                        )
                ))
                .collect(Collectors.toList());
    }
}
