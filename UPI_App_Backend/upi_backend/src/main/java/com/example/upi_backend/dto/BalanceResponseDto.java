package com.example.upi_backend.dto;

import java.math.BigDecimal;

public class BalanceResponseDto {
    private String phoneNumber;
    private BigDecimal balance;
    private String message;

    public BalanceResponseDto() {
    }

    public BalanceResponseDto(String phoneNumber, BigDecimal balance, String message) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BalanceResponseDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", message='" + message + '\'' +
                '}';
    }
}

