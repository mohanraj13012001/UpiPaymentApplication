package com.example.upi_backend.controller;

import com.example.upi_backend.dto.TransactionReponseDto;
import com.example.upi_backend.dto.TransactionRequestDto;
import com.example.upi_backend.service.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller to handle UPI transactions such as money transfer and fetching transaction history.
 */
@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Transfers the specified amount from sender to receiver as per the transaction request.
     *
     * @param dto the transaction request DTO containing details like sender, receiver, and amount
     * @return ResponseEntity with success message and HTTP 200 status
     */
    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody @Valid TransactionRequestDto dto) {
        logger.info("Processing transaction from {} to {} for amount {}", dto.getFromUserId(), dto.getToUserId(), dto.getAmount());
        transactionService.transferAmount(dto);
        return ResponseEntity.ok("Transaction successful.");
    }

    /**
     * Retrieves the list of all transactions.
     *
     * @return ResponseEntity with list of transaction response DTOs and HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<TransactionReponseDto>> getAllTransactions() {
        logger.info("Fetching all transactions");
        List<TransactionReponseDto> transactionResponseDtoList = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactionResponseDtoList, HttpStatus.OK);
    }
}
