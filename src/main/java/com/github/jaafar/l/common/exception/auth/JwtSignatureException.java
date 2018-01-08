package com.github.jaafar.l.common.exception.auth;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * Created by ace on 2017/9/15.
 */
public class JwtSignatureException extends BaseException {
    public JwtSignatureException(String s) {
        super(s, CommonConstants.EX_TOKEN_SIGN_CODE);
    }
}
