package com.devdynamo.order.service;

import com.devdynamo.constant.OrderStatus;
import com.devdynamo.entity.Order;
import com.devdynamo.entity.OrderItem;
import com.devdynamo.order.repository.OrderRepository;
import com.devdynamo.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@DubboService
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        assert order != null;
        try{
            orderRepository.save(order);
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Order> listOrder(Long userId) {
        assert userId != null;
        List<Order> orders=null;
        try{
            orders = orderRepository.findOrdersByUserId(userId);
        } catch(Exception e){
            log.error(e.getMessage());
        }
        assert orders != null;
        return orders;
    }

    @Override
    public Order getOrder(String orderId) {
        assert orderId != null;
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(String orderId) {
        assert orderId != null;
        try{
            orderRepository.deleteById(orderId);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public Float calculateOrder(String orderId) {
        assert orderId != null;
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("订单不存在"));

        return order.getOrderItems().stream()
                    .map(OrderItem::getCost)
                    .reduce(0, Integer::sum)
                    .floatValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderStatus(String orderId, OrderStatus status) {
        assert orderId != null;
        assert status != null;
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("订单不存在"));

        order.setStatus(status);
        orderRepository.save(order);
        log.info("订单状态已更新: orderId={}, status={}", orderId, status);
    }
}
