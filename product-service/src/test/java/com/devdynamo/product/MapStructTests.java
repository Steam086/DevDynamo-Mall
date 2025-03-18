package com.devdynamo.product;

import com.devdynamo.entity.product.Category;
import com.devdynamo.entity.product.Product;
import com.devdynamo.product.elasticsearch.document.EsProduct;
import com.devdynamo.product.mapstruct.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MapStructTests {


    ProductMapper productMapper = ProductMapper.INSTANCE;
    @Test
    public void testMapStruct() {
        Product product = new Product();
        Category category = new Category();
        category.setName("catego1234ry");
        Category category1 = new Category();
        category1.setName("c1");

        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);
        product.setCategories(categories);
        EsProduct esProduct = productMapper.dbToEs(product);
        System.out.println(esProduct);
    }
}
