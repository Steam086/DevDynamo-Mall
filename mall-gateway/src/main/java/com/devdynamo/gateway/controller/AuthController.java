package com.devdynamo.gateway.controller;


import com.devdynamo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @DubboReference
    private AuthService authService;

    @PostMapping()
    void getToken(Integer userId) {
        String s = authService.deliverToken(1234);
        log.info(s);
    }
}
