package com.devdynamo.product;

import com.devdynamo.entity.Category;
import com.devdynamo.product.entity.elasticsearch.Product;
import com.devdynamo.product.repository.ProductElasticsearchRepository;
import com.devdynamo.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ElasticSearchTests {

    @Autowired
    private ProductElasticsearchRepository productRepository;

    @Test
    public void createProduct() {
        List<String> categories = Arrays.asList("cat1", "cat2", "cat3", "cat4", "cat5");
        Product product = new Product();
        product.setCategories(categories);
        product.setDescription("test product");
        product.setName("test name");
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
//        productRepository.save(product);
        // Save to Elasticsearch
        productRepository.save(product);
    }
}
