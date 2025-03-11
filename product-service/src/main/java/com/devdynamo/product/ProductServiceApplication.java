package com.devdynamo.product;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;


@SpringBootApplication(exclude = {
        ElasticsearchDataAutoConfiguration.class,
        ElasticsearchRestClientAutoConfiguration.class
})
@EntityScan("com.devdynamo.entity.product")
//@EnableDubbo
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
