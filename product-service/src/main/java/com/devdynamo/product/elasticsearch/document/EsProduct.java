package com.devdynamo.product.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;

/**
 * Entity for Elasticsearch
 */
@Data
@Document(indexName = "product")
public class EsProduct{
    /**
     * 试试String,可能 Long也可以
     */
    @Id
    private String id;

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

    /**
     * 不能用LocalDateTIme
     */
    @Field(type = FieldType.Date)
    private Instant createTime;

    @Field(type = FieldType.Date)
    private Instant updateTime;
}
