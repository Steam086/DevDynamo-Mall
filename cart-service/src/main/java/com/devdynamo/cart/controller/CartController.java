package com.devdynamo.cart.controller;

import com.devdynamo.dto.CartDTO;
import com.devdynamo.service.CartService;
import com.devdynamo.service.AuthService;
import com.devdynamo.service.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    
    private final CartService cartService;
    
    @DubboReference
    private AuthService authService;

    @DubboReference
    private JwtUtil jwtUtil;
    
    @PostMapping("/create")
    public ResponseEntity<String> createCart(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7); // 去掉"Bearer "前缀
            if (!authService.verifyToken(jwtToken)) {
                return ResponseEntity.badRequest().body("无效的token");
            }
            
            // 从token中获取userId (这里假设userId是Integer类型)
            Integer userId = Integer.valueOf(jwtUtil.getUsernameFromToken(jwtToken));
            String cartId = cartService.createCart(userId.longValue());
            return ResponseEntity.ok(cartId);
        } catch (Exception e) {
            log.error("创建购物车失败", e);
            return ResponseEntity.badRequest().body("创建失败：" + e.getMessage());
        }
    }
    
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> clearCart(
            @PathVariable String cartId,
            @RequestHeader("Authorization") String token) {
        try {
            if (!authService.verifyToken(token.substring(7))) {
                return ResponseEntity.badRequest().build();
            }
            cartService.clearCart(cartId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("清空购物车失败", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartInfo(
            @PathVariable String cartId,
            @RequestHeader("Authorization") String token) {
        try {
            if (!authService.verifyToken(token.substring(7))) {
                return ResponseEntity.badRequest().build();
            }
            CartDTO cart = cartService.getCartInfo(cartId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            log.error("获取购物车信息失败", e);
            return ResponseEntity.badRequest().build();
        }
    }
}