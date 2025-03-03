package com.devdynamo.order.controller;


import com.devdynamo.entity.Order;
import com.devdynamo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 创建订单
     * @return 创建的订单
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try{
            orderService.createOrder(order);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }



    /**
     * 修改订单信息，可选
     * @return
     */
    @PutMapping
    public ResponseEntity<?> updateOrder(){
        return ResponseEntity.ok().build();
    }


    @GetMapping("/list")
    public ResponseEntity<?> getOrders(){
        List<Order> orders =null;
        try{
            orders = orderService.listOrder(1L);
        }
        catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("错误，找不到订单！");
        }
        return ResponseEntity.ok().body(orders);
    }


}
