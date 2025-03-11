package com.devdynamo.product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ElasticSearchTests {

//    @Autowired
//    private ProductElasticsearchRepository productRepository;

//    @Test
//    public void createProduct() {
        // TODO fails to execute with Elasticsearch Repository
//        Elasticsearch
//        List<String> categories = Arrays.asList("cat1", "cat2", "cat3", "cat4", "cat5");
//        Product product = new Product();
////        product.setCategories(categories);
//        product.setDescription("test product");
//        product.setName("test name");
////        product.setCreateTime(LocalDateTime.now());
////        product.setUpdateTime(LocalDateTime.now());
////        productRepository.save(product);
//        // Save to Elasticsearch
//        productRepository.save(product);
//    }

    // 依然失败
    @Test
    public void create() throws IOException {
        // URL and API key
        String serverUrl = "https://localhost:9200";
        String apiKey = "VnVhQ2ZHY0JDZGJrU...";

// Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

// And create the API client
        ElasticsearchClient esClient = new ElasticsearchClient(transport);

// Use the client...

// Close the client, also closing the underlying transport object and network connections.
        esClient.close();

    }
}
