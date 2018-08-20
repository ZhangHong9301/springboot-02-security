package com.bonc.security.service;

import com.bonc.security.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springboot-02-security
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-20 16:05
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    /**
     * @Description: 修改当前用户的密码
     * @param: [pwd]  
     * @Return: java.lang.String
     * @Author: Mr.Zhang 
     * @Date: 2018/8/20 16:56
     */
    public String updatePassword(String pwd){

        String username = getCurrentUsername();

        System.out.println("username:"+username);

        if (username != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(pwd);
            userMapper.updatePassword(username,password);
            return "Update success!";
        }
        return "Update Faile! User isn't exist!";
    }

    /**
     * @Description: 获取当前用户名
     * @param: []  
     * @Return: java.lang.String
     * @Author: Mr.Zhang 
     * @Date: 2018/8/20 16:55
     */
    public String getCurrentUsername() {

        return SecurityContextHolder.getContext().getAuthentication().getName();

    }
}
