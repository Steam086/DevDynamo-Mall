package com.devdynamo.auth.constant;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class TokenConstant {

    private static final String SECRET_KEY ="amRrc2hhZmtnc2Fkamhnamhnc2praGdqZmdqaHNkYWdmamhzZGdhZmpraGdTS0pHQVNISkRHSkhTR0ZISktHQVNISktHZmhqZHNhZ2hqa2dzZGhqYWtnZmhqYUdTSEpGR0hKU0RIR0pGR0hKU0FER0pIRkdIU0E=";

    public static final long EXPIRE_TIME=1000 * 60 * 60 * 24;

    public static final long RENEW_TIME=1000 * 60 * 60 * 24;

    @Bean
    public SecretKey keys() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
