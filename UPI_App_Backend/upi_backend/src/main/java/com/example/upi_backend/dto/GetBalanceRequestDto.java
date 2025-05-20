package com.example.upi_backend.dto;

import jakarta.validation.constraints.NotNull;

public class GetBalanceRequestDto {

    @NotNull
    private String phoneNumber;
    private String secretPin;

    public GetBalanceRequestDto() {
    }

    public GetBalanceRequestDto(String phoneNumber, String secretPin) {
        this.phoneNumber = phoneNumber;
        this.secretPin = secretPin;
    }

    public @NotNull String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecretPin() {
        return secretPin;
    }

    public void setSecretPin(String secretPin) {
        this.secretPin = secretPin;
    }

    @Override
    public String toString() {
        return "GetBalanceRequestDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", secretPin='" + secretPin + '\'' +
                '}';
    }
}
