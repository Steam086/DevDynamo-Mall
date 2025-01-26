package com.devdynamo.user.config;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CasbinConfig {

    @Bean
    public Enforcer enforcer() throws Exception {
        String modelPath = "casbin/rbac_model.conf";
        String policyPath = "casbin/rbac_policy.csv";
        
        Resource modelResource = new ClassPathResource(modelPath);
        Resource policyResource = new ClassPathResource(policyPath);
        
        Enforcer enforcer = new Enforcer(modelResource.getFile().getPath(), 
                                       policyResource.getFile().getPath());
        
        // 打印所有策略
        log.info("Loaded policies:");
        for (List<String> policy : enforcer.getPolicy()) {
            log.info(policy.toString());
        }
        
        // 打印所有角色
        log.info("Loaded roles:");
        for (List<String> role : enforcer.getGroupingPolicy()) {
            log.info(role.toString());
        }
        
        return enforcer;
    }
}
