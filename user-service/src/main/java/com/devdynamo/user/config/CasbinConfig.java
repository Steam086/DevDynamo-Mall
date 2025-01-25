package com.devdynamo.user.config;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class CasbinConfig {

    @Bean
    public Enforcer enforcer() throws Exception {
        String modelPath = "casbin/rbac_model.conf";
        String policyPath = "casbin/rbac_policy.csv";
        
        Resource modelResource = new ClassPathResource(modelPath);
        Resource policyResource = new ClassPathResource(policyPath);
        
        return new Enforcer(modelResource.getFile().getPath(), 
                          policyResource.getFile().getPath());
    }
}
