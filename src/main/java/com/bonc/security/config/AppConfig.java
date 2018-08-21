package com.bonc.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-02-security
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-21 15:49
 */
@Configuration
public class AppConfig{
    @Bean
    public ObjectMapper ObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper;
    }
}
