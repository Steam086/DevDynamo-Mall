package com.devdynamo.order.service;

import com.devdynamo.constant.OrderStatus;
import com.devdynamo.entity.Order;
import com.devdynamo.entity.OrderItem;
import com.devdynamo.order.repository.OrderRepository;
import com.devdynamo.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// @Service
@DubboService
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public List<Order> listOrder(Long userId) {
        return List.of();
    }

    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public void markOrderAsPaid(Long userId, Long orderId) {

    }

    @Override
    public Float calculateOrder(String orderId) {
        Order order = orderRepository.findById(Long.valueOf(orderId)).orElseThrow(() -> new RuntimeException("订单不存在"));

        return order.getOrderItems().stream()
                    .map(OrderItem::getCost)
                    .reduce(0, Integer::sum)
                    .floatValue();
    }

    @Override
    @Transactional
    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(Long.valueOf(orderId)).orElseThrow(() -> new RuntimeException("订单不存在"));

        order.setStatus(status);
        orderRepository.save(order);
        log.info("订单状态已更新: orderId={}, status={}", orderId, status);
    }
}
