package com.devdynamo.product.mq;

import com.devdynamo.product.elasticsearch.repository.EsProductRepository;
import com.devdynamo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private static final String QUEUE_NAME = "queue-product";

    private final ProductRepository productRepository;

    private final EsProductRepository esProductRepository;

    /**
     * 将数据写入到Elasticsearch的逻辑
     * @param message
     */
    @RabbitListener(queues = QUEUE_NAME)
    public void listen(final String message) {
        log.info("Received product ID from RabbitMQ: {}", message);

        // TODO 查找数据库


        //TODO 转存到 Elasticsearch

    }
}
