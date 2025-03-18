package com.devdynamo.product.controller;


import com.devdynamo.entity.product.Category;
import com.devdynamo.entity.product.Product;
import com.devdynamo.product.repository.CategoryRepository;
import com.devdynamo.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 测试专用Controller
 */
@RestController
@RequestMapping("/empty")
public class EmptyController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    String hello = "Hello World!";

    public EmptyController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    ResponseEntity<?> shit(){
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }

    @GetMapping("/query")
    ResponseEntity<?> query(){
        List<Product> all = productRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /**
     * 找到就复用，找不到就新建
     * @param product
     * @return
     */
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Product product){
        List<Category> categories = (product.getCategories());
        categories = categories.stream()
                .map(category -> {
                    Category byName = categoryRepository.findByName(category.getName());
                    return Objects.requireNonNullElseGet(byName, () -> categoryRepository.save(category));
                }).toList();
        product.setCategories(categories);
        Product save = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }
}
