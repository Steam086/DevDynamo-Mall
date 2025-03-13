package com.devdynamo.product;

import com.devdynamo.product.elasticsearch.document.EsProduct;
import com.devdynamo.product.elasticsearch.repository.EsProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * 基本通过了测试
 */
@SpringBootTest
public class ElasticSearchTests {

    @Autowired
    private EsProductRepository productRepository;

    @Test
    public void createProduct() {
        List<String> categories = Arrays.asList("cat1", "cat2", "cat3", "cat4", "cat5");
        EsProduct product = new EsProduct();
        product.setCategories(categories);
        product.setDescription("test product");
        product.setName("test name");
        product.setCreateTime(Instant.now());
        product.setUpdateTime(Instant.now());
        productRepository.save(product);
        // Save to Elasticsearch
        productRepository.save(product);
    }


//    Error creating bean with name 'productRepository' defined
//    in com.devdynamo.product.repository.ProductRepository defined in
//    @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration:
//    Not a managed type: class com.devdynamo.product.elasticsearch.document.EsProduct
    @Test
    public void query() throws IOException {
        productRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void deleteProduct() throws IOException {
        productRepository.deleteAll();
    }


}
