package com.github.jaafar.l.demo.exs.jwt;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * Created by ace on 2017/9/15.
 */
public class JwtTokenExpiredException extends BaseException {
    public JwtTokenExpiredException(String s) {
        super(s, CommonConstants.EX_TOKEN_EXPIRE_CODE);
    }
}
