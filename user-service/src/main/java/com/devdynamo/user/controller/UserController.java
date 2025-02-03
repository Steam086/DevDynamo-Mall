package com.devdynamo.user.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.devdynamo.dto.UserLoginDTO;
import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.entity.User;
import com.devdynamo.service.UserService;
import com.devdynamo.user.util.JwtUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/user")


public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // 注册
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO registerDTO, 
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            User user = userService.register(registerDTO);
            return ResponseEntity.ok(user.getName() + "用户注册成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // 登出
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String bearerToken) {
        try {
            log.info("Received logout request with token: {}", bearerToken);
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);
                String username = jwtUtil.getUsernameFromToken(token);
                if (username != null) {
                    userService.logout(username);
                    return ResponseEntity.ok("登出成功");
                }
            }
            return ResponseEntity.badRequest().body("无效的token");
        } catch (Exception e) {
            log.error("Logout failed: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // 登录
    @PostMapping("/login")
    public ResponseEntity<?> logout(@Valid @RequestBody UserLoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            String token = userService.login(loginDTO);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // 获取用户信息
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String bearerToken) {
        // TODO: 创建 UserInfoDTO 用于过滤敏感信息
        // TODO: 需要包含：用户名、昵称、邮箱、创建时间等，过滤密码等敏感字段
        try {
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);
                String username = jwtUtil.getUsernameFromToken(token);
                if (username != null) {
                    User user = userService.getUserInfo(username);
                    return ResponseEntity.ok(user);
                }
            }
            return ResponseEntity.badRequest().body("invalid token");
        } catch (Exception e) {
            log.error("Get user info failed: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
