package com.devdynamo.cart.service;

import com.devdynamo.dto.CartDTO;
import com.devdynamo.entity.Cart;
import com.devdynamo.service.CartService;
import com.devdynamo.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Slf4j
@DubboService
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    
    @Override
    public String createCart(Long userId) {
        Cart cart = new Cart();
        cart.setId("cart_" + UUID.randomUUID().toString());
        cart.setUserId(userId);
        cartRepository.save(cart);
        return cart.getId();
    }

    @Override
    public void clearCart(String cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new RuntimeException("购物车不存在"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDTO getCartInfo(String cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new RuntimeException("购物车不存在"));
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cart, cartDTO);
        return cartDTO;
    }
}