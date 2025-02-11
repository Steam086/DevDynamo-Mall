package com.devdynamo.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public void test() {
//        String key = redisTemplate.opsForValue().get("key");
//        redisTemplate.opsForValue().set("key", "");
//        String k11 = redisTemplate.opsForValue().get("key");
//        System.out.println(Objects.isNull(key));
//        System.out.println(Objects.isNull(k11));
    }
}
