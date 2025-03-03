package com.devdynamo.payment.schedule;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.devdynamo.constant.PaymentStatus;
import com.devdynamo.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentScheduler {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void handleExpiredPayments() {
        log.info("开始处理过期支付...");
        paymentRepository.findByStatusAndExpireTimeBefore(
            PaymentStatus.PENDING, 
            LocalDateTime.now()
        ).forEach(payment -> {
            payment.setStatus(PaymentStatus.EXPIRED);
            paymentRepository.save(payment);
            log.info("支付已过期: 订单ID={}", payment.getOrderId());
        });
    }
}