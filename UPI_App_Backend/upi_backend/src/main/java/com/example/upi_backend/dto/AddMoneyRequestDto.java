package com.example.upi_backend.dto;

import java.math.BigDecimal;

public class AddMoneyRequestDto {

    private String phoneNumber;
    private BigDecimal amount;

    private String secretPin;
    public AddMoneyRequestDto() {
    }

    public AddMoneyRequestDto(String phoneNumber, BigDecimal amount, String secretPin) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.secretPin = secretPin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSecretPin() {
        return secretPin;
    }

    public void setSecretPin(String secretPin) {
        this.secretPin = secretPin;
    }

    @Override
    public String toString() {
        return "AddMoneyRequestDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", amount=" + amount +
                ", secretPin='" + secretPin + '\'' +
                '}';
    }
}
