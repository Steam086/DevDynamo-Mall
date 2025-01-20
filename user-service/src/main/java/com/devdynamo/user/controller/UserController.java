package com.devdynamo.user.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.devdynamo.user.model.entity.User;

@RestController
@RequestMapping("/api/user")


public class UserController {
    // 注册
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok("User registered successfully");
    }
    
    // 登录
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return ResponseEntity.ok("Login successful");
    }
    
    // 登出
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
    
    // 获取用户信息
    @GetMapping("/info")
    public ResponseEntity<String> getUserInfo() {   
        return ResponseEntity.ok("User info retrieved successfully");
    }
    
    // 更新用户信息
    @PutMapping("/update")
    public ResponseEntity<String> updateUserInfo(@RequestBody User user) {
        return ResponseEntity.ok("User info updated successfully");
    }
    
    // 删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("User deleted successfully");
    }
}
