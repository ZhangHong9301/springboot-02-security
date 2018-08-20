package com.bonc.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: 页面跳转控制器
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-16 14:26
 */
@Controller
public class KungfuController {
    private final String PREFIX = "pages/";
    
    /**
     * @Description: 欢迎页
     * @param: []  
     * @Return: java.lang.String
     * @Author: Mr.Zhang 
     * @Date: 2018/8/16 14:31
     */
    @GetMapping("/")
    public String index(){
        return "welcome";
    }

    /**
     * @Description: 登录页
     * @param: []  
     * @Return: java.lang.String
     * @Author: Mr.Zhang 
     * @Date: 2018/8/16 14:33
     */
    @GetMapping("/userlogin")
    public String loginPage(){
        return PREFIX+"login";
    }

    /**
     * @Description: level1页面映射
     * @param: [path]  
     * @Return: java.lang.String
     * @Author: Mr.Zhang 
     * @Date: 2018/8/16 14:33
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path){
        return PREFIX +"level1/"+path;
    }

    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path){
        return PREFIX +"level2/"+path;
    }

    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path){
        return PREFIX +"level3/"+path;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "Hello SpringBoot!";
    }
}
