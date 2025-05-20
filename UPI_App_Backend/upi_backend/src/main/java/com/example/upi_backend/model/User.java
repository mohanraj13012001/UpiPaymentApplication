package com.example.upi_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 10)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String username;

    private boolean upiEnabled;

    private BigDecimal balance;

    private Long maxTransfer;

    private Double maxAmount;

    @Column(name = "secret_pin", nullable = false)
    private String secretPin;

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
    @JsonManagedReference("sent")
    private List<PaymentTransfer> sentTransfers;

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
    @JsonManagedReference("received")
    private List<PaymentTransfer> receivedTransfers;

    public User() {
    }

    public User(Long id, String phoneNumber, String username, boolean upiEnabled, BigDecimal balance, Long maxTransfer, Double maxAmount, String secretPin, List<PaymentTransfer> sentTransfers, List<PaymentTransfer> receivedTransfers) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.upiEnabled = upiEnabled;
        this.balance = balance;
        this.maxTransfer = maxTransfer;
        this.maxAmount = maxAmount;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getMaxTransfer() {
        return maxTransfer;
    }

    public void setMaxTransfer(Long maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
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
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", upiEnabled=" + upiEnabled +
                ", balance=" + balance +
                ", maxTransfer=" + maxTransfer +
                ", maxAmount=" + maxAmount +
                ", secretPin='" + secretPin + '\'' +
                ", sentTransfers=" + sentTransfers +
                ", receivedTransfers=" + receivedTransfers +
                '}';
    }
}
