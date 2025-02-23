package com.devdynamo.service;

import com.devdynamo.entity.Order;
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
     * @return 一个包含该用户订单的List
     */
    List<Order> listOrder(Long userId);

    /**
     * 删除订单
     * @param id 待删除的订单id
     */
    void deleteOrder(Long id);

    /**
     * 将订单标记为已支付
     * @param userId 用户id
     * @param orderId 订单id
     */
    void markOrderAsPaid(Long userId, Long orderId);

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
//syntax = "proto3";
//
//        package order;
//
//option go_package = "order";
//
//service OrderService {
//rpc PlaceOrder(PlaceOrderReq) returns (PlaceOrderResp) {}
//rpc ListOrder(ListOrderReq) returns (ListOrderResp) {}
//rpc MarkOrderPaid(MarkOrderPaidReq) returns (MarkOrderPaidResp) {}
//        }
//
//message Address {
//string street_address = 1;
//string city = 2;
//string state = 3;
//string country = 4;
//int32 zip_code = 5;
//}
//
//message PlaceOrderReq {
//uint32 user_id = 1;
//string user_currency = 2;
//
//Address address = 3;
//string email = 4;
//repeated OrderItem order_items = 5;
//        }
//
//message OrderItem {
//cart.CartItem item = 1;
//float cost = 2;
//}
//
//message OrderResult {
//string order_id = 1;
//}
//
//message PlaceOrderResp {
//OrderResult order = 1;
//}
//
//message ListOrderReq {
//uint32 user_id = 1;
//}
//
//message Order {
//repeated OrderItem order_items = 1;
//string order_id = 2;
//uint32 user_id = 3;
//string user_currency = 4;
//Address address = 5;
//string email = 6;
//int32 created_at = 7;
//}
//
//message ListOrderResp {
//repeated Order orders = 1;
//        }
//
//message MarkOrderPaidReq {
//uint32 user_id = 1;
//string order_id = 2;
//}
//
//message MarkOrderPaidResp {}
