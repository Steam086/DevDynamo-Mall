package com.devdynamo.payment.dto;
import lombok.Data;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ChargeResponse {
    private String transactionId;
}