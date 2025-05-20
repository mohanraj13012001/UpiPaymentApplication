package com.example.upi_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private LocalDateTime loginTime;
    private String ipAddress;

    public LoginHistory() {
    }

    public LoginHistory(Long id, String phoneNumber, LocalDateTime loginTime, String ipAddress) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.loginTime = loginTime;
        this.ipAddress = ipAddress;
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

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "LoginHistory{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", loginTime=" + loginTime +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
