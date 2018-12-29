package com.bonc.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Create by Mr.ZXF
 * on 2018-12-29 10:01
 */
public class CaptchaException extends AuthenticationException {
    private static final long serialVersionUID = -7932793974645209799L;

    public CaptchaException(String msg) {
        super(msg);

    }

}
