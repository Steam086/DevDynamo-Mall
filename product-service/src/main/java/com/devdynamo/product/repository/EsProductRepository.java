package com.devdynamo.product.repository;

import com.devdynamo.product.document.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
}
