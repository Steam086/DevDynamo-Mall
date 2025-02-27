package com.devdynamo.order;

import com.devdynamo.entity.Address;
import com.devdynamo.entity.Order;
import com.devdynamo.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderRepositoryTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void save() {
        Address dummyAddress = new Address();

        Order dummyOrder = new Order();
        dummyOrder.setAddress(dummyAddress);
        orderRepository.save(dummyOrder);
    }

    @Test
    void findAll(){
//        List<Order> orders = orderRepository.findAll();
        orderRepository.findAll().forEach(System.out::println);
        orderRepository.findById(1L).ifPresent(System.out::println);
    }

}
