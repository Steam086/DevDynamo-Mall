package com.devdynamo.service;

import com.devdynamo.constant.OrderStatus;
import com.devdynamo.entity.Order;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     * @param order 订单的信息
     */
    void createOrder(Order order);

    /**
     * 获取订单
     * 获取指定用户的订单
     * @return 一个包含该用户订单的List，如果找不到，返回一个空列表
     */
    @NotNull
    List<Order> listOrder(Long userId);

    /**
     * 获取指定id的订单
     * @param orderId 订单id
     * @return Order 对应id的订单，搜索不到返回null
     */
    Order getOrder(String orderId);

    /**
     * 删除订单
     * @param orderId 待删除的订单id
     */
    void deleteOrder(String orderId);

    /**
     * 订单结算
     * @param orderId 订单ID
     * @return 结算金额
     */
    Float calculateOrder(String orderId);
    
    /**
     * 更新订单支付状态
     * @param orderId 订单ID
     * @param status 订单状态
     */
    void updateOrderStatus(String orderId, OrderStatus status);


    //    /**
//     * 将订单标记为已支付
//     * @param userId 用户id
//     * @param orderId 订单id
//     */
//    void markOrderAsPaid(Long userId, String orderId);
//    /**
//     * 可选，修改订单信息
//     * @param order 待修改的订单
//     */
//    void updateOrder(Order order);
//    /**
//     * 高级，订单定时取消
//     */
//    void cancel(){
//
//    }
}
