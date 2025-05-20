package com.example.upi_backend.exception;

public class MaxBalanceExceededException extends RuntimeException {
    public MaxBalanceExceededException(String message) {
        super(message);
    }
}