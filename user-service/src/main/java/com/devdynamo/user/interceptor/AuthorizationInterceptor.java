package com.devdynamo.user.interceptor;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devdynamo.user.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import java.util.List;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final Enforcer enforcer;
    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    public AuthorizationInterceptor(Enforcer enforcer, JwtUtil jwtUtil) {
        this.enforcer = enforcer;
        this.jwtUtil = jwtUtil;
    }

   
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, 
                            @NonNull HttpServletResponse response, 
                            @NonNull Object handler) {
        String token = request.getHeader("Authorization");
        log.info("Received token: {}", token);
        
        if (token == null || !token.startsWith("Bearer ")) {
            log.warn("No valid Authorization header found");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        
        try {
            token = token.substring(7);
            log.info("Processing token after removing Bearer prefix: {}", token);
            
            if (!jwtUtil.validateToken(token)) {
                log.error("Invalid token");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            
            String username = jwtUtil.getUsernameFromToken(token);
            if (username == null) {
                log.error("Failed to extract username from token");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            
            String path = request.getRequestURI();
            String method = request.getMethod();
            
            log.info("Auth Check - Username: {}, Path: {}, Method: {}", username, path, method);
            
            // 检查用户角色
            List<String> roles = enforcer.getRolesForUser(username);
            log.info("User roles: {}", roles);
            
            // 检查权限策略
            List<List<String>> policies = enforcer.getPolicy();
            log.info("All policies: {}", policies);
            
            // 检查权限
            boolean hasPermission = enforcer.enforce(username, path, method);
            log.info("Permission check result: {} for {}", hasPermission, username);
            
            if (hasPermission) {
                return true;
            } else {
                log.warn("Permission denied for user: {} accessing: {} with method: {}", 
                        username, path, method);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        } catch (Exception e) {
            log.error("Error processing request: ", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
