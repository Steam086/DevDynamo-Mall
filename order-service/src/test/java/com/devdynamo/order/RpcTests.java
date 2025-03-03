package com.devdynamo.order;

import com.devdynamo.service.AuthService;
import com.devdynamo.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RpcTests {
    @DubboReference
    private AuthService authService;


    @Test
    public void testAuth2() {
        authService.deliverToken(10);
    }

}
