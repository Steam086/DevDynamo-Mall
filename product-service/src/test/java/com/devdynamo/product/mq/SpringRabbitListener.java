package com.devdynamo.product.mq;

import com.alibaba.nacos.shaded.com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringRabbitListener {
    private static final String queueName = "product-queue";


    @RabbitListener(queues = queueName)
    public void listen(final String message) {
        log.info("Received Message: {}", message);
    }
}
