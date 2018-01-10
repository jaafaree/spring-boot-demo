package com.github.jaafar.l.ratelimiter;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/10 12:16
 */
public class RateLimitException extends BaseException {
    public RateLimitException(String message) {
        super(message, CommonConstants.EX_RATELIMIT_CODE);
    }
}
