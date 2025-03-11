package com.devdynamo.service;

import com.devdynamo.entity.product.Product;

import java.util.List;

public interface ProductService {

    /**
     * 创建商品
     * @param product 待创建的商品信息
     * @return 返回创建好的商品对象
     */
    Product create(Product product);

    /**
     * 更新商品信息
     * @param product 待更新的商品信息，id不能为空
     *
     */
    void update(Product product);

    /**
     * 删除商品
     * @param id 商品id
     *
     */
    void delete(Long id);

    /**
     * 分页列出商品
     * @param page 页码
     * @param pageSize 每页个数
     * @param category 类别
     * @return 该页的商品
     */
    List<Product> listProducts(int page, int pageSize,String category);

    /**
     * 根据商品id获取单个商品
     * @param id 商品id
     * @return id对应的商品
     */
    Product GetProduct(Long id);

    //TODO 这里根据实际查询需求更改接口传入的参数
    /**
     * 查找商品
     * @param query 查询语句
     * @return 满足条件的商品列表
     */
    List<Product> SearchProducts(String query);
}
