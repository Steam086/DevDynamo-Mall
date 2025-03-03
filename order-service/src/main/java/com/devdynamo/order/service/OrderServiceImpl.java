package com.devdynamo.order.service;

import com.devdynamo.entity.Order;
import com.devdynamo.order.repository.OrderRepository;
import com.devdynamo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@DubboService
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(Order order) {
        try{
            orderRepository.save(order);
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Order> listOrder(Long userId) {
        List<Order> orders = List.of();
        try{
            orders = orderRepository.findOrdersByUserId(userId);
        } catch(Exception e){
            log.error(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Long id) {
        try{
            orderRepository.deleteById(id);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void markOrderAsPaid(Long userId, Long orderId) {

    }
}
