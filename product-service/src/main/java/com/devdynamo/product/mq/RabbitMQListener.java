package com.devdynamo.product.mq;

import com.devdynamo.entity.product.Product;
import com.devdynamo.product.elasticsearch.document.EsProduct;
import com.devdynamo.product.elasticsearch.repository.EsProductRepository;
import com.devdynamo.product.mapstruct.ProductMapper;
import com.devdynamo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {

    private static final String QUEUE_NAME = "queue-product";

    private final ProductRepository productRepository;

    private final EsProductRepository esProductRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public RabbitMQListener(ProductRepository productRepository, EsProductRepository esProductRepository) {
        this.productRepository = productRepository;
        this.esProductRepository = esProductRepository;
    }

    /**
     * 将数据写入到Elasticsearch的逻辑
     * @param message
     */
    @RabbitListener(queues = QUEUE_NAME)
    public void listen(final Product message) {
        log.debug("Received product ID from RabbitMQ: {}", message);
        //TODO 转存到 Elasticsearch
        try{
            // 如果此时这里出现问题
            EsProduct esProduct = productMapper.dbToEs(message);
            esProductRepository.save(esProduct);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
