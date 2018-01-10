package com.github.jaafar.l.ratelimiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/10 12:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RateLimit {
    /**
     * 单位时间内允许访问次数,默认MAX_VALUE
     * @return
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 单位时间，毫秒，默认1min
     * @return
     */
    int time() default 1;
}
