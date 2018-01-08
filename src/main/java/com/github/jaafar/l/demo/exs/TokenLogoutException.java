package com.github.jaafar.l.demo.exs;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/5 17:29
 */
public class TokenLogoutException extends BaseException {
    public TokenLogoutException(String message) {
        super(message, CommonConstants.EX_TOKEN_LOGOUT_CODE);
    }
}
