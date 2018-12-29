package com.bonc.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * 	1、引入SpringSecurity
 * 	2、编写SpringSecurity的配置类
 * @param:
 * @Return:
 * @Author: Mr.Zhang 
 * @Date: 2018/8/16 15:02
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling/*启动注解定时任务*/
@MapperScan("com.bonc.security.mapper")
public class Springboot02SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02SecurityApplication.class, args);
	}
}
