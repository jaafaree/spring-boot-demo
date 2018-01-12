package com.github.jaafar.l.demo.config.datasource;

import java.lang.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/11 16:35
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
