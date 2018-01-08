package com.github.jaafar.l.demo.exs;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 11:19
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
