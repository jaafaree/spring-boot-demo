package com.github.jaafar.l.demo.exs;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 10:44
 */
public class TokenErrorException extends BaseException {
    public TokenErrorException(String message) {
        super(message, CommonConstants.EX_TOKEN_ERROR_CODE);
    }
}
