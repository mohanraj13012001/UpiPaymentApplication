package com.example.upi_backend.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransactionRequestDto {

    @NotNull
    private Long fromUserId;

    @NotNull
    private Long toUserId;

    @NotNull
    @DecimalMin(value = "1.00", message = "Amount must be at least ₹1")
    @DecimalMax(value = "100000.00", message = "Amount must not exceed ₹1,00,000")
    @DecimalMax(value = "20000.00", message = "Amount must not exceed ₹20,000")
    private BigDecimal amount;

    @NotNull
    private String secretPin;


    public TransactionRequestDto() {
    }

    public TransactionRequestDto(Long fromUserId, Long toUserId, BigDecimal amount, String secretPin) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.secretPin = secretPin;
    }

    public @NotNull Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(@NotNull Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public @NotNull Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(@NotNull Long toUserId) {
        this.toUserId = toUserId;
    }

    public @NotNull @DecimalMin(value = "1.00", message = "Amount must be at least ₹1") @DecimalMax(value = "100000.00", message = "Amount must not exceed ₹1,00,000") @DecimalMax(value = "20000.00", message = "Amount must not exceed ₹20,000") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull @DecimalMin(value = "1.00", message = "Amount must be at least ₹1") @DecimalMax(value = "100000.00", message = "Amount must not exceed ₹1,00,000") @DecimalMax(value = "20000.00", message = "Amount must not exceed ₹20,000") BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull String getSecretPin() {
        return secretPin;
    }

    public void setSecretPin(@NotNull String secretPin) {
        this.secretPin = secretPin;
    }

    @Override
    public String toString() {
        return "TransactionRequestDto{" +
                "fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", amount=" + amount +
                ", secretPin='" + secretPin + '\'' +
                '}';
    }
}
