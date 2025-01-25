package com.devdynamo.user.interceptor;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.devdynamo.user.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final Enforcer enforcer;
    private final JwtUtil jwtUtil;

    public AuthorizationInterceptor(Enforcer enforcer, JwtUtil jwtUtil) {
        this.enforcer = enforcer;
        this.jwtUtil = jwtUtil;
    }

   
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, 
                        @NonNull HttpServletResponse response, 
                        @NonNull Object handler) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String username = jwtUtil.getUsernameFromToken(token);
            String path = request.getRequestURI();
            String method = request.getMethod();

            // 添加日志
            System.out.println("Username: " + username);
            System.out.println("Path: " + path);
            System.out.println("Method: " + method);

            if (enforcer.enforce(username, path, method)) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
