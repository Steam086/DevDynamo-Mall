package com.devdynamo.entity;

import com.devdynamo.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Float amount;
    private String orderId;
    private Long userId;
    private String transactionId;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    private LocalDateTime createTime;
    private LocalDateTime expireTime;  // 支付过期时间
    private LocalDateTime updateTime;
    
    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
        // 设置15分钟后过期
        this.expireTime = LocalDateTime.now().plusMinutes(15);
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}