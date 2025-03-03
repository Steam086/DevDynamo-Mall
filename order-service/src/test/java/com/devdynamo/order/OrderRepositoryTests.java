package com.devdynamo.order;

import com.devdynamo.entity.Address;
import com.devdynamo.entity.Order;
import com.devdynamo.entity.OrderItem;
import com.devdynamo.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderRepositoryTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void save() {
        Address dummyAddress = new Address();

        Order dummyOrder = new Order();
        OrderItem dummyOrderItem = new OrderItem();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(dummyOrderItem);
        dummyOrder.setAddress(dummyAddress);
        dummyOrder.setOrderItems(orderItems);
        orderRepository.save(dummyOrder);
    }

    @Test
    void findAll(){
//        List<Order> orders = orderRepository.findAll();
        orderRepository.findAll().forEach(System.out::println);
        orderRepository.findById("1").ifPresent(System.out::println);
    }

}
