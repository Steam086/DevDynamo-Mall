package com.devdynamo.product.service;


import com.devdynamo.entity.Product;
import com.devdynamo.service.ProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@DubboService
public class ProductServiceImpl implements ProductService {

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Product> listProducts(int page, int pageSize, String category) {
        return List.of();
    }

    @Override
    public Product GetProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> SearchProducts(String query) {
        return List.of();
    }
}
