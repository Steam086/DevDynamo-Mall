package com.devdynamo.product;


import com.devdynamo.product.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SpringBootApplication(exclude = {
//        ElasticsearchDataAutoConfiguration.class,
//        ElasticsearchRestClientAutoConfiguration.class
//})
@SpringBootApplication
@EntityScan("com.devdynamo.entity.product")
@EnableJpaRepositories(basePackages = "com.devdynamo.product.repository")
@EnableElasticsearchRepositories(basePackages = {"com.devdynamo.product.elasticsearch.repository"})
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
