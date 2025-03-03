package com.devdynamo.payment.service;

import java.time.LocalDate;
import java.util.UUID;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdynamo.constant.OrderStatus;
import com.devdynamo.constant.PaymentStatus;
import com.devdynamo.dto.CreditCardInfo;
import com.devdynamo.payment.repository.PaymentRepository;
import com.devdynamo.service.OrderService;
import com.devdynamo.service.PaymentService;
import com.devdynamo.entity.Payment;

import lombok.extern.slf4j.Slf4j;

@Service
@DubboService
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;

    @DubboReference
    // @Autowired
    private OrderService orderService;
    
    @Override
    @Transactional
    public String processPayment(Float amount, CreditCardInfo creditCard, String orderId, Long userId) {
        log.info("开始处理支付请求: 订单ID={}, 金额={}", orderId, amount);
        
        // 1. 验证信用卡信息
        validateCreditCard(creditCard);

        Float orderAmount = orderService.calculateOrder(orderId);
        if (!amount.equals(orderAmount)) {
            throw new RuntimeException("支付金额与订单金额不符");
        }
        
        // 2. 调用第三方支付接口(这里模拟)
        String transactionId = generateTransactionId();
        
        // 3. 保存支付记录
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setOrderId(orderId);
        payment.setUserId(userId);
        payment.setTransactionId(transactionId);
        payment.setStatus(PaymentStatus.COMPLETED);
        
        paymentRepository.save(payment);

        orderService.updateOrderStatus(orderId, OrderStatus.PAID);
        
        log.info("支付处理完成: 交易ID={}", transactionId);
        return transactionId;
    }

    @Override
    @Transactional
    public void cancelPayment(String orderId, Long userId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
            .orElseThrow(() -> new RuntimeException("支付订单不存在"));
            
        if (!payment.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此支付订单");
        }
        
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("只能取消待支付的订单");
        }
        
        payment.setStatus(PaymentStatus.CANCELLED);
        paymentRepository.save(payment);
        log.info("支付已取消: 订单ID={}", orderId);
    }

    @Override
    public Payment getPayment(String orderId) {
        return paymentRepository.findByOrderId(orderId)
            .orElseThrow(() -> new RuntimeException("支付订单不存在"));
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