package com.bonc.security.service;


import com.bonc.security.domain.Permission;
import com.bonc.security.domain.User;
import com.bonc.security.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author http://www.sm1234.cn
 * @version 1.0
 * @description cn.sm1234.security
 * @date 18/4/14
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = Logger.getLogger(MyUserDetailService.class);

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userMapper.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        //根据用户名查询当前用户所有权限
        List<Permission> permList = userMapper.findPermissionByUsername(username);
        //authorities：存放所有用户权限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Permission perm : permList) {
            GrantedAuthority authority = new SimpleGrantedAuthority(perm.getPermTag());
            authorities.add(authority);
        }
        //把所有权限赋值给user
        user.setAuthorities(authorities);

        logger.info("当前用户：" + user);
        return user;
    }
}
