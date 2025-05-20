package com.example.upi_backend.exception;

import com.example.upi_backend.dto.AddMoneyResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling custom runtime exceptions
 * across the entire application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles exceptions when the user is not found.
     *
     * @param ex the UserNotFoundException
     * @return ResponseEntity with HTTP 404 and error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AddMoneyResponseDto> handleUserNotFound(UserNotFoundException ex) {
        logger.error("User not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new AddMoneyResponseDto(null, null, ex.getMessage()));
    }

    /**
     * Handles exceptions for invalid requests such as UPI disabled,
     * invalid amount, or max balance exceeded.
     *
     * @param ex the RuntimeException (UpiDisabledException, InvalidAmountException, or MaxBalanceExceededException)
     * @return ResponseEntity with HTTP 400 and error message
     */
    @ExceptionHandler({
            UpiDisabledException.class,
            InvalidAmountException.class,
            MaxBalanceExceededException.class
    })
    public ResponseEntity<AddMoneyResponseDto> handleBadRequest(RuntimeException ex) {
        logger.error("Bad request: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AddMoneyResponseDto(null, null, ex.getMessage()));
    }
}
