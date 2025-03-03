package com.devdynamo.payment.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdynamo.constant.PaymentStatus;
import com.devdynamo.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(String orderId);
    List<Payment> findByStatusAndExpireTimeBefore(PaymentStatus status, LocalDateTime expireTime);
}