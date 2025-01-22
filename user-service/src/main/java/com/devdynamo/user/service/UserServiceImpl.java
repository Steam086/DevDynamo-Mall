package com.devdynamo.user.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.entity.User;
import com.devdynamo.service.UserService;
import com.devdynamo.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        //TODO: 密码加密

        return userRepository.save(user);
    }

}
