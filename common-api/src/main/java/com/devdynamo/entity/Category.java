package com.devdynamo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * product-service
 */
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> product;


}
