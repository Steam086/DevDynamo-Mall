package com.devdynamo.entity.cart;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "address")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * RPC remote userId
     */
    private Long userId;
    @OneToMany
    List<CartItem> items;
}
