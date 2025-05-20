package com.example.upi_backend.repositary;

import com.example.upi_backend.model.PaymentTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface PaymentTransferRepository extends JpaRepository<PaymentTransfer,Long> {
    int countByFromUserIdAndDateTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM PaymentTransfer p WHERE p.fromUser.id = :userId AND p.dateTime BETWEEN :start AND :end")
    BigDecimal findTotalAmountTransferredToday(
            @Param("userId") Long userId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
