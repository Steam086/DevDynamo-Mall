package com.devdynamo.product;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试RabbitMQ的队列
 */
@SpringBootTest
public class RabbitMQTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        String queueName = "product-queue";
        String message = "Hello World";

        rabbitTemplate.convertAndSend(queueName, message);
    }
}
