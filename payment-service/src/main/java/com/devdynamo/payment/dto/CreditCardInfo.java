package com.devdynamo.payment.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreditCardInfo {
    @NotBlank(message = "信用卡号不能为空")
    private String creditCardNumber;
    
    @NotNull(message = "CVV不能为空")
    @Min(value = 100, message = "CVV必须是3位数")
    @Max(value = 999, message = "CVV必须是3位数")
    private Integer cvv;
    
    @NotNull(message = "过期年份不能为空")
    private Integer expirationYear;
    
    @NotNull(message = "过期月份不能为空")
    @Min(value = 1, message = "月份必须在1-12之间")
    @Max(value = 12, message = "月份必须在1-12之间")
    private Integer expirationMonth;
}