package com.devdynamo.dto;

import lombok.Data;

@Data
public class CreditCardInfo {
    private String creditCardNumber;
    private Integer cvv;
    private Integer expirationYear;
    private Integer expirationMonth;
}