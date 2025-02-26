package com.devdynamo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 订单填写的地址
 */
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String name;

//    private String phoneNumber;

    private String country;

    private String state;

    private String city;

    private String street;

    private Integer zipcode;
}
