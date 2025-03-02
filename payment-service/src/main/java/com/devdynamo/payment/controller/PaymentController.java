package com.devdynamo.payment.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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
}