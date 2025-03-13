package com.devdynamo.entity.order;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 商品Id，用于查询商品微服务
    private Long productId;
    // 数据库Order
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;// string `gorm:"size:256;index"`
    // 订购数量
    Integer quantity;
    // 总花费金额
    Integer cost;
}
