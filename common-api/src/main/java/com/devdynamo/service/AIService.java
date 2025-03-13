package com.devdynamo.service;

import com.devdynamo.entity.order.Order;

import java.util.List;

public interface AIService {
    /**
     * 订单查询
     * @param query 查询的prompt
     */
    List<Order> queryOrder(String query);

    /**
     * 模拟自动下单 TODO 什么叫模拟自动下单？和LLM有什么关系
     * 猜测应该是根据用户的prompt来将需要购买的商品加入并生成订单
     * @param order
     */
    void simulateOrder(String order);

}
