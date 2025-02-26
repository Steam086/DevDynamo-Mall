package com.devdynamo.service;

public interface AIService {
    /**
     * AI订单查询
     * @param query 用户的查询语句
     * @return AI的回答
     */
    String queryOrder(String query);

    /**
     * AI模拟下单
     * @param requirements 下单需求描述
     * @return 模拟订单信息
     */
    String simulateOrder(String requirements);
}
