package com.devdynamo.product.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

import java.time.Duration;

@Configuration
public class ElasticsearchConfig{
    @Bean
    ElasticsearchClient elasticsearchClient() {
        // URL and API key
        String serverUrl = "http://localhost:9200";

        // Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{
//                        new BasicHeader("Authorization", "ApiKey " )
                })
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchTemplate elasticsearchRestTemplate() {
        return new ElasticsearchTemplate(elasticsearchClient());
    }

//    @Override
//    public ClientConfiguration clientConfiguration() {
//        return ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .withConnectTimeout(Duration.ofSeconds(5))
//                .withSocketTimeout(Duration.ofSeconds(30))
////                .withBasicAuth("username", "password")
//                // 启用SSL（可选）
////                .usingSsl()
//                // 配置代理（可选）
////                .withProxy("proxyhost:8080")
//                .build();
//    }
}
