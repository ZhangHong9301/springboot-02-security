package com.bonc.security.controller;

import com.bonc.security.service.UserService;
import com.bonc.security.common.utils.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springboot-02-security
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-20 16:17
 */
@Controller
@Api("用户信息接口")
@Slf4j
public class UserController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

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


    @GetMapping("/captcha")
    public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {



        // 设置响应格式为图片
        response.setContentType("image/jpeg");
        // 禁止图图片缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // HttpSession session = request.getSession();
        Captcha captcha = new Captcha(80, 29, 4, 8,5*60);
        // session.setAttribute("code", captcha.getCode());

        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, captcha);
        captcha.write(response.getOutputStream());
        log.info("make new captcha: "+ captcha.getCode());
    }
}
