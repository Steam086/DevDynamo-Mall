package com.devdynamo.constant;

public enum PaymentStatus {
    PENDING,    // 待支付
    COMPLETED,  // 支付完成
    CANCELLED,  // 已取消
    EXPIRED,    // 已过期
    FAILED      // 支付失败
}
