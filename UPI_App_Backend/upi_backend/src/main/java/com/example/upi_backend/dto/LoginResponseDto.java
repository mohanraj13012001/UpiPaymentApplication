package com.example.upi_backend.dto;

public class LoginResponseDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private boolean upiEnabled;
    private double balance;
    private UserDto userDto;
    private String message;
    public LoginResponseDto() {
    }

    public LoginResponseDto(Long id, String name, String phoneNumber, boolean upiEnabled, double balance, UserDto userDto, String message) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.upiEnabled = upiEnabled;
        this.balance = balance;
        this.userDto = userDto;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", upiEnabled=" + upiEnabled +
                ", balance=" + balance +
                ", userDto=" + userDto +
                ", message='" + message + '\'' +
                '}';
    }
}
