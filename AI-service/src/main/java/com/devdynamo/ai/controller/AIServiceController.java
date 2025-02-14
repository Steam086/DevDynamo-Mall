package com.devdynamo.ai.controller;

import com.devdynamo.ai.dto.AiRequestDTO;
import com.devdynamo.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIServiceController {
    
    private final AIService aiService;
    
    @PostMapping("/query-order")
    public ResponseEntity<String> queryOrder(@RequestBody AiRequestDTO request) {
        log.info("收到AI订单查询请求: {}", request.getQuery());
        try {
            String response = aiService.queryOrder(request.getQuery());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("AI订单查询失败", e);
            return ResponseEntity.badRequest().body("查询失败：" + e.getMessage());
        }
    }
    
    @PostMapping("/simulate-order")
    public ResponseEntity<String> simulateOrder(@RequestBody AiRequestDTO request) {
        log.info("收到AI模拟下单请求: {}", request.getQuery());
        try {
            String response = aiService.simulateOrder(request.getQuery());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("AI模拟下单失败", e);
            return ResponseEntity.badRequest().body("模拟下单失败：" + e.getMessage());
        }
    }
}