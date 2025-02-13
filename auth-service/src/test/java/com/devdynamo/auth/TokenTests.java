package com.devdynamo.auth;

import com.devdynamo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

@SpringBootTest
@Slf4j
public class TokenTests {
    @Autowired
    AuthService authService;
    String token;
    Integer userId = 123456;
    @Test
    public void deliverTokenTest() {
        token = authService.deliverToken(userId);
        assert token != null;
        log.info("获取到userId:{} 对应的token:{}", userId,token);

    }


    @Test
    public void verifyTokenTest() {
        token = authService.deliverToken(userId);
        log.info("获取到userId:{} 对应的token:{}", userId,token);
        boolean b = authService.verifyToken(token);
    }
}
