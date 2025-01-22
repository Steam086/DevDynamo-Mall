package com.devdynamo.auth.jwt;


import com.devdynamo.auth.constant.TokenConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenUtil {

    private final SecretKey key;

    private final RedisTemplate<String, String> redisTemplate;

    // Generate JWT token
    public String getToken(String userId) {
        String token = Jwts.builder()
                .subject(userId)
                .claim("isAdmin", false)
                .signWith(key)
                .expiration(new java.util.Date(System.currentTimeMillis() + TokenConstant.EXPIRE_TIME))
                .compact();
        // Set expire time
        redisTemplate.opsForValue().set(userId, token, Duration.ofMillis(TokenConstant.EXPIRE_TIME));
        return token;
    }


    public Map<String, Object> parseToken(String token) {
        HashMap<String, Object> map = new HashMap<>();

        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        String userId = claimsJws.getPayload().getSubject();
        // Check expire time
        String o = redisTemplate.opsForValue().get(userId);
        if(!Objects.nonNull(o)) {
            throw new ExpiredJwtException(claimsJws.getHeader(), claimsJws.getPayload(), "Token is expired");
        }
        // TODO How to replace the map ?
        map.put("userId", userId);
        map.put("isAdmin", claimsJws.getPayload().get("isAdmin"));
        return map;
    }
}

