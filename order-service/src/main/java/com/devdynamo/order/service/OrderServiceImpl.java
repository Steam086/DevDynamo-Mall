package com.devdynamo.order.service;

import com.devdynamo.entity.Order;
import com.devdynamo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void createOrder(Order order) {

    }

    @Override
    public List<Order> listOrder(Long userId) {
        return List.of();
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public void markOrderAsPaid(Long userId, Long orderId) {

    }
}
