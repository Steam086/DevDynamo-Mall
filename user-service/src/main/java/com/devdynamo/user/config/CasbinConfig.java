package com.devdynamo.user.config;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasbinConfig {

    @Bean
    public Enforcer enforcer() {
        Enforcer enforcer = new Enforcer("src/main/resources/casbin/rbac_model.conf");

        enforcer.addPolicy("admin", "/api/user/*", "*");
        enforcer.addPolicy("user", "/api/user/info", "GET");
        enforcer.addPolicy("user", "/api/user/update", "PUT");

        enforcer.addGroupingPolicy("siyuan", "admin");
        enforcer.addGroupingPolicy("nobody", "user");

        return enforcer;
    }
}
