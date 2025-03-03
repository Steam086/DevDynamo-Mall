package com.devdynamo.user.service;

import java.util.concurrent.TimeUnit;
import java.util.List;

import com.devdynamo.service.AuthService;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devdynamo.dto.UserLoginDTO;
import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.dto.UserUpdateDTO;
import com.devdynamo.entity.User;
import com.devdynamo.service.UserService;
import com.devdynamo.user.repository.UserRepository;
import com.devdynamo.user.util.JwtUtil;
import org.casbin.jcasbin.main.Enforcer;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;
    private final JwtUtil jwtUtil;
    private static final long TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 days
    private final Enforcer enforcer;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RedisTemplate<String, String> redisTemplate, JwtUtil jwtUtil, Enforcer enforcer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
        this.enforcer = enforcer;
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

        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 为新用户分配基础角色
        enforcer.addGroupingPolicy(user.getUsername(), "user");
        enforcer.savePolicy();

        return userRepository.save(user);
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("用户名或密码错误"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 生成token时可以包含用户角色信息
        String token = jwtUtil.generateToken(user.getUsername());
        String redisKey = "token:" + user.getUsername();
        redisTemplate.opsForValue().set(redisKey, token, TOKEN_EXPIRATION, TimeUnit.SECONDS);

        return token;
    }

    public void logout(String username) {
        String redisKey = "token:" + username;
        redisTemplate.delete(redisKey);
    }

    public User getUserInfo(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    public boolean validateToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            return false;
        }

        String username = jwtUtil.getUsernameFromToken(token);
        String redisKey = "token:" + username;
        String storedToken = redisTemplate.opsForValue().get(redisKey);

        return token.equals(storedToken);
    }

    @Override
    public void deleteUser(String currentUsername, String targetUsername) {
        // 检查是否为管理员或者是否为自己的账号
        List<String> roles = enforcer.getRolesForUser(currentUsername);
        if (!roles.contains("admin") && !currentUsername.equals(targetUsername)) {
            throw new RuntimeException("没有权限删除该用户");
        }

        User user = userRepository.findByUsername(targetUsername)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        // 删除用户的角色
        enforcer.deleteRolesForUser(targetUsername);
        
        // 删除Redis中的token
        String redisKey = "token:" + targetUsername;
        redisTemplate.delete(redisKey);
        
        // 删除用户
        userRepository.delete(user);
    }

    @Override
    public User updateUser(String username, UserUpdateDTO updateDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        // 如果要更新邮箱，检查新邮箱是否已被使用
        if (updateDTO.getEmail() != null && !updateDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(updateDTO.getEmail())) {
                throw new RuntimeException("该邮箱已被使用");
            }
            user.setEmail(updateDTO.getEmail());
        }
        
        // 更新其他字段
        if (updateDTO.getFirstName() != null) {
            user.setFirstName(updateDTO.getFirstName());
        }
        if (updateDTO.getLastName() != null) {
            user.setLastName(updateDTO.getLastName());
        }
        if (updateDTO.getPhone() != null) {
            user.setPhone(updateDTO.getPhone());
        }
        
        return userRepository.save(user);
    }

}
