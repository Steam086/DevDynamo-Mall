package com.devdynamo.cart.repository;

import com.devdynamo.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    
}
