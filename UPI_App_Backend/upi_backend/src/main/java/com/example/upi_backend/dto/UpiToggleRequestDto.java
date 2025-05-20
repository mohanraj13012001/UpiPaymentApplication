package com.example.upi_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpiToggleRequestDto {
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    private boolean upiEnabled;

    public UpiToggleRequestDto() {
    }

    public UpiToggleRequestDto(String phoneNumber, boolean upiEnabled) {
        this.phoneNumber = phoneNumber;
        this.upiEnabled = upiEnabled;
    }

    public @NotBlank(message = "Phone number is required") @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required") @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUpiEnabled() {
        return upiEnabled;
    }

    public void setUpiEnabled(boolean upiEnabled) {
        this.upiEnabled = upiEnabled;
    }
}
