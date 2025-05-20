package com.example.upi_backend.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequestDto {

    @NotNull
    private String phoneNumber;
    @NotNull
    private String secretPin;

    private boolean upiEnabled;
    public LoginRequestDto() {
    }

    public LoginRequestDto(String phoneNumber, String secretPin, boolean upiEnabled) {
        this.phoneNumber = phoneNumber;
        this.secretPin = secretPin;
        this.upiEnabled = upiEnabled;
    }

    public @NotNull String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotNull String getSecretPin() {
        return secretPin;
    }

    public void setSecretPin(@NotNull String secretPin) {
        this.secretPin = secretPin;
    }

    public boolean isUpiEnabled() {
        return upiEnabled;
    }

    public void setUpiEnabled(boolean upiEnabled) {
        this.upiEnabled = upiEnabled;
    }

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", secretPin='" + secretPin + '\'' +
                ", upiEnabled=" + upiEnabled +
                '}';
    }
}
