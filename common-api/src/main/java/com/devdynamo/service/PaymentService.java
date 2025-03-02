package com.devdynamo.service;
import com.devdynamo.dto.CreditCardInfo;

public interface PaymentService {
    /**
     * 处理信用卡支付
     * @param amount 支付金额
     * @param creditCard 信用卡信息
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 交易ID
     */
    String processPayment(Float amount, CreditCardInfo creditCard, String orderId, Long userId);
}