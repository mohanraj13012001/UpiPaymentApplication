package com.example.upi_backend.dto;

import com.example.upi_backend.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentTransferDto {
    private Long id;

    private BigDecimal amount;
    private LocalDateTime dateTime;
    private User fromUser;
    private User toUser;

    public PaymentTransferDto() {
    }

    public PaymentTransferDto(Long id, BigDecimal amount, LocalDateTime dateTime, User fromUser, User toUser) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "PaymentTransferDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }
}
