package com.example.upi_backend.dto;

import java.math.BigDecimal;

public class AddMoneyResponseDto {

    private String phoneNumber;
    private BigDecimal updatedBalance;
    private String message;

    public AddMoneyResponseDto() {
    }

    public AddMoneyResponseDto(String phoneNumber, BigDecimal updatedBalance, String message) {
        this.phoneNumber = phoneNumber;
        this.updatedBalance = updatedBalance;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(BigDecimal updatedBalance) {
        this.updatedBalance = updatedBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AddMoneyResponseDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", updatedBalance=" + updatedBalance +
                ", message='" + message + '\'' +
                '}';
    }
}
