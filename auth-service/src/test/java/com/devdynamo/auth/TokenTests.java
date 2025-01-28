package com.devdynamo.auth;

import com.devdynamo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TokenTests {
    @Autowired
    AuthService authService;
    @Test
    public void test() {
        String s = authService.deliverToken(123456);
        log.info(s);
        Boolean b = authService.verifyToken(s);
        log.info(b.toString());
    }
}
