package com.devdynamo.product.service;


import com.devdynamo.entity.product.Product;
import com.devdynamo.product.elasticsearch.document.EsProduct;
import com.devdynamo.product.elasticsearch.repository.EsProductRepository;
import com.devdynamo.product.repository.ProductRepository;
import com.devdynamo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
//@DubboService
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final EsProductRepository esProductRepository;

    private final RabbitTemplate rabbitTemplate;

    private static final String PRODUCT_QUEUE = "product-queue";

    /**
     * 方案一： 在数据存到数据库之后向MQ发送待保存的product的id,
     * MQ消费者接受到id之后查数据库并将内容同步到Elasticsearch
     * @param product 待创建的商品信息
     * @return 创建成功的Product
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product create(Product product) {
        Product savedProduct;
        try{
            savedProduct = productRepository.save(product);
            Long id = savedProduct.getId();
            // 将id发送到MQ,消费者处理同步逻辑
            rabbitTemplate.convertAndSend(PRODUCT_QUEUE, id);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        return product;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Product> listProducts(int page, int pageSize, String category) {
        return List.of();
    }

    @Override
    public Product GetProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> SearchProducts(String query) {
        return List.of();
    }
}
