package com.devdynamo.payment.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.devdynamo.entity.Payment;
import com.devdynamo.payment.dto.ChargeRequest;
import com.devdynamo.payment.dto.ChargeResponse;
import com.devdynamo.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/charge")
    public ResponseEntity<?> charge(@Valid @RequestBody ChargeRequest request) {
        try {
            String transactionId = paymentService.processPayment(
                request.getAmount(),
                request.getCreditCard(),
                request.getOrderId(),
                request.getUserId()
            );
            
            return ResponseEntity.ok(new ChargeResponse(transactionId));
        } catch (Exception e) {
            log.error("支付处理失败", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/order/cancel")
    public ResponseEntity<?> cancelPayment(@RequestParam String orderId, @RequestParam Long userId) {
        try {
            paymentService.cancelPayment(orderId, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("取消支付失败", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getPayment(@PathVariable String orderId) {
        try {
            Payment payment = paymentService.getPayment(orderId);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            log.error("获取支付信息失败", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}