package com.devdynamo.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    private String id;
    
    private Long userId;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;
}

