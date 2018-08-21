package com.bonc.security.controller;

import com.bonc.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: springboot-02-security
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-20 16:17
 */
@Controller
@Api("用户信息接口")
public class UserController {

    @Resource
    UserService userService;

    @ResponseBody
    @GetMapping("/update/user")
    @ApiOperation(value = "修改密码接口")
    public String setPassword(@RequestParam(value = "password") String password){

        String isOK = userService.updatePassword(password);

        return isOK;
    }

    @GetMapping("/update")
    public String pagePassword(){

        return "pages/updatepassword";
    }
}
