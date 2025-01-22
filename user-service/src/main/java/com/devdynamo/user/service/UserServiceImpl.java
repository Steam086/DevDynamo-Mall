package com.devdynamo.user.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devdynamo.dto.UserLoginDTO;
import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.entity.User;
import com.devdynamo.service.UserService;
import com.devdynamo.user.repository.UserRepository;
import com.devdynamo.user.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;
    private final JwtUtil jwtUtil;
    private static final long TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 days

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RedisTemplate<String, String> redisTemplate, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(UserRegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new RuntimeException("username is already exists");
        }

        // 检查邮箱是否已经存在
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("email is already exists");
        }

        if (!EmailValidator.getInstance().isValid(registerDTO.getEmail())) {
            throw new RuntimeException("邮箱格式不正确");
        }


        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("用户名或密码错误"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // generate token
        String token = jwtUtil.generateToken(user.getUsername());
        redisTemplate.opsForValue().set(token, user.getUsername(), TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);

        return jwtUtil.generateToken(user.getUsername());
    }

    public void logout(String token) {
        String redisKey = "token: " + token;
        redisTemplate.delete(redisKey);
    }

    public boolean validateToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            return false;
        }

        String username = jwtUtil.getUsernameFromToken(token);
        String redisKey = "token: " + username;
        String storedToken = redisTemplate.opsForValue().get(redisKey);

        return token.equals(storedToken);
    }

}
