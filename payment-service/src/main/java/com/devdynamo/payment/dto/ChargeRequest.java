package com.devdynamo.payment.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import com.devdynamo.dto.CreditCardInfo;  // 使用common-api中的CreditCardInfo

@Data
public class ChargeRequest {
    @NotNull(message = "支付金额不能为空")
    @Positive(message = "支付金额必须大于0")
    private Float amount;
    
    @Valid
    @NotNull(message = "信用卡信息不能为空")
    private CreditCardInfo creditCard;
    
    @NotBlank(message = "订单ID不能为空")
    private String orderId;
    
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
