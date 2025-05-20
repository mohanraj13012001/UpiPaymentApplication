package com.example.upi_backend.dto;

import com.example.upi_backend.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionReponseDto {
    private Long transactionId;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private UserDto fromUser;
    private UserDto toUser;

    public TransactionReponseDto() {
    }

    public TransactionReponseDto(Long transactionId, BigDecimal amount, LocalDateTime dateTime, UserDto fromUser, UserDto toUser) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserDto getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserDto fromUser) {
        this.fromUser = fromUser;
    }

    public UserDto getToUser() {
        return toUser;
    }

    public void setToUser(UserDto toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "TransactionReponseDto{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }
}
