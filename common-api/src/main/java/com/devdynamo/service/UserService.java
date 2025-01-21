package com.devdynamo.service;

import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.entity.User;

public interface UserService {
    User register(UserRegisterDTO registerDTO);
}
