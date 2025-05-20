package com.example.upi_backend.dto;

import com.example.upi_backend.model.PaymentTransfer;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class UserDto {

    private Long id;
    private String phoneNumber;
    private String username;
    private boolean upiEnabled;

    @DecimalMax(value = "100000.00", message = "Balance cannot exceed ₹1,00,000")
    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    private String secretPin;

    private List<PaymentTransfer> sentTransfers;
    private List<PaymentTransfer> receivedTransfers;

    public UserDto() {
    }

    public UserDto(Long id, String phoneNumber, String username, boolean upiEnabled, BigDecimal balance, String secretPin, List<PaymentTransfer> sentTransfers, List<PaymentTransfer> receivedTransfers) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.upiEnabled = upiEnabled;
        this.balance = balance;
        this.secretPin = secretPin;
        this.sentTransfers = sentTransfers;
        this.receivedTransfers = receivedTransfers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUpiEnabled() {
        return upiEnabled;
    }

    public void setUpiEnabled(boolean upiEnabled) {
        this.upiEnabled = upiEnabled;
    }

    public @DecimalMax(value = "100000.00", message = "Balance cannot exceed ₹1,00,000") @NotNull(message = "Balance is required") BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(@DecimalMax(value = "100000.00", message = "Balance cannot exceed ₹1,00,000") @NotNull(message = "Balance is required") BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretPin() {
        return secretPin;
    }

    public void setSecretPin(String secretPin) {
        this.secretPin = secretPin;
    }

    public List<PaymentTransfer> getSentTransfers() {
        return sentTransfers;
    }

    public void setSentTransfers(List<PaymentTransfer> sentTransfers) {
        this.sentTransfers = sentTransfers;
    }

    public List<PaymentTransfer> getReceivedTransfers() {
        return receivedTransfers;
    }

    public void setReceivedTransfers(List<PaymentTransfer> receivedTransfers) {
        this.receivedTransfers = receivedTransfers;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", upiEnabled=" + upiEnabled +
                ", balance=" + balance +
                ", secretPin='" + secretPin + '\'' +
                ", sentTransfers=" + sentTransfers +
                ", receivedTransfers=" + receivedTransfers +
                '}';
    }
}
