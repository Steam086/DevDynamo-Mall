package com.devdynamo.entity.product;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    // TODO 尚不完整
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String picture;

    private Double price;

    @ManyToMany
    private List<Category> categories;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;


    @PrePersist
    public void prePersist() {
        this.createTime = Instant.now();
        this.updateTime = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = Instant.now();
    }
}
