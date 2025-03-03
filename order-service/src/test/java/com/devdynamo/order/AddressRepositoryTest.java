package com.devdynamo.order;

import com.devdynamo.entity.Address;
import com.devdynamo.entity.Order;
import com.devdynamo.order.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    AddressRepository repository;

    @Test
    public void findAll() {
        List<Address> addresses = repository.findAll();
        List<Order> orders = List.of();
        addresses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        repository.findById(1L).ifPresent(System.out::println);
    }

}
