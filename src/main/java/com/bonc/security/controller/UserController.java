package com.bonc.security.controller;

import com.bonc.security.service.UserService;
import com.bonc.security.util.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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


    @GetMapping("/code")
    public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 设置响应格式为图片
        response.setContentType("image/jpeg");
        // 禁止图图片缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        Captcha captcha = new Captcha(80, 29, 4, 8);
        session.setAttribute("code", captcha.getCode());
        captcha.write(response.getOutputStream());
    }
}
