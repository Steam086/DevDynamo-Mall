package com.devdynamo.product.entity.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity for Elasticsearch
 */
@Data
@Document(indexName = "product")
public class Product {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String picture;

    @Field(type = FieldType.Float)
    private Double price;

    @Field(type = FieldType.Keyword)
    private List<String> categories;

    @Field(type = FieldType.Date)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date)
    private LocalDateTime updateTime;
}
