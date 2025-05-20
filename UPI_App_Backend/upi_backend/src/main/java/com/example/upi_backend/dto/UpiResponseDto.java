package com.example.upi_backend.dto;

public class UpiResponseDto {
    private Long userId;
    private String message;

    public UpiResponseDto() {
    }

    public UpiResponseDto(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
