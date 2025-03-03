package com.devdynamo.user;

import com.devdynamo.service.AuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RpcTests {

    @DubboReference
    AuthService authService;

    @Test
    public void testAuth() {
        String s = authService.deliverToken(10);
        System.out.println(s);

        boolean b = authService.verifyToken(s);

        System.out.println(b);
        assert b;
    }
}
