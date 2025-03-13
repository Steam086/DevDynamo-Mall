package com.devdynamo.service;

import com.devdynamo.entity.cart.Cart;
import com.devdynamo.entity.cart.CartItem;

/**
 * 购物车服务接口，实现了
 * - 向购物车中添加商品
 * - 获取购物车信息
 * - 清空购物车
 */
public interface CartService {
    /**
     * 商品加入购物车，如果购物车不存在，则新建购物车
     * @param userId 用户id
     * @param cartItem 加入的商品
     */
    void addItem(Long userId,CartItem cartItem);

    /**
     * 获取购物车信息
     * @param userId 用户id
     */
    Cart getCart(Long userId);

    /**
     * 清空购物车
     * @param userId 用户id
     */
    void emptyCart(Long userId);


}
