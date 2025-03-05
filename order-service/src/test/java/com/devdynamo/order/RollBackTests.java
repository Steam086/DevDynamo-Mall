package com.devdynamo.order;

import com.devdynamo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RollBackTests {
    // TODO 测试SpringBoot的Transaction的回滚功能

    @Autowired
    OrderService orderService;

    @Test
    public void testRollBack() {
        orderService.deleteOrder("sadada112e2423");

    }
}
