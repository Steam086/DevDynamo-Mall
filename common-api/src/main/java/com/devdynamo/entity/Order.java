package com.devdynamo.entity;

import com.devdynamo.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 订单创建者
    private Long userId;

    // 订单填写的地址
    @ManyToOne
//    @JoinColumn(name = "address_id")
    Address address;

    @OneToMany
    List<OrderItem> orderItems;

    // 订单状态，枚举
    OrderStatus status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;


    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }

}
