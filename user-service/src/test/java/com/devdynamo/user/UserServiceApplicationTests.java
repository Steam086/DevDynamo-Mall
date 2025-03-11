package com.devdynamo.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.entity.user.User;
import com.devdynamo.user.repository.UserRepository;
import com.devdynamo.service.UserService;
import org.casbin.jcasbin.main.Enforcer;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@SpringBootTest(properties = {
    "spring.cloud.nacos.config.enabled=false",
    "spring.cloud.nacos.discovery.enabled=false"
})
@EnableAutoConfiguration(exclude = {
    RedisAutoConfiguration.class,
    RedisRepositoriesAutoConfiguration.class
})
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private Enforcer enforcer;

    @MockBean(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    void setup() {
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });
        when(redisTemplate.opsForValue()).thenReturn(null);  // ✅ Mock Redis 操作
    }

    @Test
    void testSuccessfulRegistration() {
        // 准备测试数据
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        registerDTO.setUsername("testuser");
        registerDTO.setPassword("Test123456");
        registerDTO.setEmail("test@example.com");
        
        // 执行注册
        User result = userService.register(registerDTO);
        
        // 验证结果
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }
}

