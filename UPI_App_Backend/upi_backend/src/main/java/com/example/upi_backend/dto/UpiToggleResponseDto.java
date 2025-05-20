package com.example.upi_backend.dto;

public class UpiToggleResponseDto {
    private String phoneNumber;
    private boolean upiEnabled;
    private String message;

    public UpiToggleResponseDto(String phoneNumber, boolean upiEnabled, String message) {
        this.phoneNumber = phoneNumber;
        this.upiEnabled = upiEnabled;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUpiEnabled() {
        return upiEnabled;
    }

    public void setUpiEnabled(boolean upiEnabled) {
        this.upiEnabled = upiEnabled;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UpiToggleResponseDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", upiEnabled=" + upiEnabled +
                ", message='" + message + '\'' +
                '}';
    }
}
