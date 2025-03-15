package com.devdynamo.product;

import com.devdynamo.entity.product.Category;
import com.devdynamo.product.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DatabaseTests {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void saveTest() {
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setName("test0");
        categories.add(category);
        Category category1 = new Category();
        category1.setName("test1");
        categories.add(category1);
        categoryRepository.save(category);
//        categoryRepository.saveAll(categories);
    }

}
