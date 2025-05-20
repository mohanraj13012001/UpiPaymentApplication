package com.example.upi_backend.repositary;

import com.example.upi_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UpiRepositary extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
