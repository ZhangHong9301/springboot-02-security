package com.bonc.security.authentication;

import com.bonc.security.controller.UserController;
import com.bonc.security.exception.CaptchaException;
import com.bonc.security.common.utils.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Mr.ZXF
 * on 2018-12-28 17:46
 */
@Slf4j
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenctiationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("RequestURI: " + httpServletRequest.getRequestURI());
        // 是一个登陆请求
        if (StringUtils.equals("/userlogin", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "POST")) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (CaptchaException e) {
                // 有异常就返回自定义失败处理器
                e.printStackTrace();
                authenctiationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        // 不是一个登录请求，不做校验 直接通过
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        Captcha codeInSession = (Captcha) sessionStrategy.getAttribute(request, UserController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "captcha");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new CaptchaException("验证码不能为空！");
        }

        if (codeInSession == null) {
            throw new CaptchaException("验证码不存在");
        }

        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, UserController.SESSION_KEY);
            throw new CaptchaException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new CaptchaException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, UserController.SESSION_KEY);

    }

    public AuthenticationFailureHandler getAuthenctiationFailureHandler() {
        return authenctiationFailureHandler;
    }

    public void setAuthenctiationFailureHandler(AuthenticationFailureHandler authenctiationFailureHandler) {
        this.authenctiationFailureHandler = authenctiationFailureHandler;
    }

}
