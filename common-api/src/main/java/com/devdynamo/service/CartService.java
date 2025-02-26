package com.devdynamo.service;

import com.devdynamo.dto.CartDTO;

public interface CartService {
    /**
     * 创建购物车
     * @param userId 用户ID
     * @return 购物车ID
     */
    String createCart(Long userId);

    /**
     * 清空购物车
     * @param cartId 购物车ID
     */
    void clearCart(String cartId);

    /**
     * 获取购物车信息
     * @param cartId 购物车ID
     * @return 购物车信息
     */
    CartDTO getCartInfo(String cartId);
}