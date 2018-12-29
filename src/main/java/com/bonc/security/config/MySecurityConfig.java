package com.bonc.security.config;

import com.bonc.security.authentication.CaptchaFilter;
import com.bonc.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * @program: springboot-02-security
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-08-16 15:08
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    MyUserDetailService myUserDetailService;

    // @Resource
    // AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    /**
     * @Description: 定义授权规则
     * @param: [http]
     * @Return: void
     * @Author: Mr.Zhang
     * @Date: 2018/8/16 15:20
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CaptchaFilter captchaFilter = new CaptchaFilter();
        // captchaFilter.setAuthenctiationFailureHandler(myAuthenticationFailureHandler);

        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/", "/userlogin", "/captcha").permitAll()
                .antMatchers("/hello", "/update").authenticated()
                .antMatchers("/level1/**").hasAuthority("VIP1")
                .antMatchers("/level2/**").hasAuthority("VIP2")
                .antMatchers("/level3/**").hasAuthority("VIP3")
                .antMatchers("/**").authenticated(); // 都需要身份认证



        //开启自动配置的登录功能
        //1、/login来到登录页
        //2、重定向到/login?error页面表示登录失败
        //3、。。
        //4、默认post形式的/login代表处理登录
        //5、一旦定制loginPage、那么loginPage 的post请求就是登录
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin")  // 自定义登录页面
                .failureUrl("/userlogin")
                // .failureHandler(myAuthenticationFailureHandler)
                .permitAll();

        //开启自动配置的注销功能
        //1、访问/logout表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能
        //登录成功以后，将cookie发给浏览器报错，
        http.rememberMe().rememberMeParameter("remeber");

        http.csrf().disable();
    }

    /**
     * @Description: 定义认证规则
     * @param: [auth]
     * @Return: void
     * @Author: Mr.Zhang
     * @Date: 2018/8/16 15:19
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
//                .and()
//                .withUser("lisi").password("123456").roles("VIP2","VIP3")
//                .and()
//                .withUser("admin").password("123456").roles("VIP1","VIP2","VIP3");

        auth.userDetailsService(myUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }


}
