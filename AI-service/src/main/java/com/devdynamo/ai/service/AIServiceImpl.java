package com.devdynamo.ai.service;

import com.devdynamo.entity.Order;
import com.devdynamo.service.AIService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
@DubboService
public class AIServiceImpl implements AIService {
    AIService aiService;
    @Value("${doubao.api_key}")
    private String apiKey;

    @Override
    public List<Order> queryOrder(String query) {
        // TODO: 实现豆包API调用逻辑
        return null;
//        return "AI订单查询结果";
    }

    @Override
    public void simulateOrder(String requirements) {
        // TODO: 实现豆包API调用逻辑
//        return "AI模拟订单结果";
    }
}