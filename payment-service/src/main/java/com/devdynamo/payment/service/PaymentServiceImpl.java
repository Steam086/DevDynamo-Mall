package com.devdynamo.payment.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdynamo.dto.CreditCardInfo;
import com.devdynamo.payment.repository.PaymentRepository;
import com.devdynamo.service.PaymentService;
import com.devdynamo.entity.Payment;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    @Transactional
    public String processPayment(Float amount, CreditCardInfo creditCard, String orderId, Long userId) {
        log.info("开始处理支付请求: 订单ID={}, 金额={}", orderId, amount);
        
        // 1. 验证信用卡信息
        validateCreditCard(creditCard);
        
        // 2. 调用第三方支付接口(这里模拟)
        String transactionId = generateTransactionId();
        
        // 3. 保存支付记录
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setOrderId(orderId);
        payment.setUserId(userId);
        payment.setTransactionId(transactionId);
        
        paymentRepository.save(payment);
        
        log.info("支付处理完成: 交易ID={}", transactionId);
        return transactionId;
    }
    
    private void validateCreditCard(CreditCardInfo creditCard) {
        // 实现信用卡验证逻辑
        if(creditCard.getExpirationYear() < LocalDate.now().getYear() || 
           (creditCard.getExpirationYear() == LocalDate.now().getYear() && 
            creditCard.getExpirationMonth() < LocalDate.now().getMonthValue())) {
            throw new RuntimeException("信用卡已过期");
        }
    }
    
    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}