package com.github.jaafar.l.common.exception.auth;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * Created by ace on 2017/9/15.
 */
public class JwtIllegalArgumentException extends BaseException {
    public JwtIllegalArgumentException(String s) {
        super(s, CommonConstants.EX_TOKEN_ILLEGAL_CODE);
    }
}
