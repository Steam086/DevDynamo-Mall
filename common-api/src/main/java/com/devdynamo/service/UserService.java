package com.devdynamo.service;

import com.devdynamo.dto.UserRegisterDTO;
import com.devdynamo.dto.UserLoginDTO;
import com.devdynamo.dto.UserUpdateDTO;
import com.devdynamo.entity.user.User;

/**
 * 这是用户服务的API接口，用于处理用户相关的业务逻辑
 * @author Siyuan Zheng
 * @version 1.0
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param registerDTO 包含注册信息的DTO对象
     * @return 注册成功的用户实体
     */
    User register(UserRegisterDTO registerDTO);

    /**
     * 用户登录
     *
     * @param loginDTO 包含登录信息的DTO对象
     * @return 登录成功后生成的token
     */
    String login(UserLoginDTO loginDTO);

    /**
     * 用户登出
     *
     * @param token 用户的认证token
     */
    void logout(String token);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return 用户实体信息
     */
    User getUserInfo(String username);

    /**
     * 删除用户
     *
     * @param currentUsername 当前操作的用户名
     * @param targetUsername 要删除的目标用户名
     */
    void deleteUser(String currentUsername, String targetUsername);

    /**
     * 更新用户信息
     *
     * @param username 要更新的用户名
     * @param updateDTO 包含更新信息的DTO对象
     * @return 更新后的用户实体
     */
    User updateUser(String username, UserUpdateDTO updateDTO);
}
