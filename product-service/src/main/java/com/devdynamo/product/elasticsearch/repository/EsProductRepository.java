package com.devdynamo.product.elasticsearch.repository;

import com.devdynamo.product.elasticsearch.document.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, String> {
}
