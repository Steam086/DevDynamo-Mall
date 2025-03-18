package com.devdynamo.product.mapstruct;

import com.devdynamo.entity.product.Category;
import com.devdynamo.entity.product.Product;
import com.devdynamo.product.elasticsearch.document.EsProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source="categories", target = "categories", qualifiedByName = "categoryToStr")
    EsProduct dbToEs(Product product);

    @Named("categoryToStr")
    static List<String> categoryToStr(List<Category> category) {
        if(category == null || category.isEmpty()) {
            return null;
        }
        return category.stream().map(Category::getName).collect(Collectors.toList());
    }
}
