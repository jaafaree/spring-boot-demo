package com.github.jaafar.l.demo.exs;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 11:15
 */
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message, CommonConstants.EX_USER_NOTFOUND_CODE);
    }
}
