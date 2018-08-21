package com.bonc.security.controller;

import com.bonc.security.domain.Permission;
import com.bonc.security.domain.User;
import com.bonc.security.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-02-security
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-21 14:22
 */
@Api(description = "Test API")
@RestController
public class SwaggerTestController {

    @Resource
    UserMapper userMapper;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/say")
    @ApiOperation("测试方法Hello")
    public String hello(){
        return "Hello! It's a test!";
    }


    @GetMapping("/user/{id}")
    @ApiOperation("查询某个用户")
    @ApiImplicitParam(name = "id",value = "用户名")
    public String getUser(@PathVariable(value = "id") String userName) throws JsonProcessingException {

        User user = userMapper.findByUsername(userName);

        if(user!=null) {
            //根据用户名查询当前用户所有权限
            List<Permission> permList = userMapper.findPermissionByUsername(userName);
            //authorities：存放所有用户权限
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            for (Permission perm : permList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(perm.getPermTag());
                authorities.add(authority);
            }
            //把所有权限赋值给user
            user.setAuthorities(authorities);
        }

        String stringUser = objectMapper.writeValueAsString(user);
        return stringUser;
    }
}
