package com.devdynamo.auth.service;

import com.devdynamo.auth.jwt.TokenUtil;
import com.devdynamo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Map;
import java.util.Objects;

@Slf4j
@DubboService
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final TokenUtil tokenUtil;

    @Override
    public String deliverToken(Integer userId) {
        assert userId != null;
        String token=null;
        try{
            token = tokenUtil.getToken(userId.toString());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        log.debug("token is:{}", token);
        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        assert token != null;
        try{
            Map<String, Object> stringStringMap = tokenUtil.parseToken(token);
            log.debug("token resolve:{}", stringStringMap);
        }
        catch(Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
