package com.devdynamo.service;

import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.dto.UserLoginDTO;
import com.devdynamo.entity.User;

public interface UserService {
    User register(UserRegisterDTO registerDTO);
    String login(UserLoginDTO loginDTO);
    void logout(String token);
    User getUserInfo(String username);
    void deleteUser(String currentUsername, String targetUsername);
}
