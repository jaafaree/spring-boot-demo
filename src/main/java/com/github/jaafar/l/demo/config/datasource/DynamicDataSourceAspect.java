package com.github.jaafar.l.demo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/11 16:35
 */
@Slf4j
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(ds.name())) {
            log.error("DataSource [{}] doesn't exist, use default DataSource [{}]", ds.name());
        } else {
            // 切换数据源
            DynamicDataSourceContextHolder.setDataSourceKey(ds.name());
            log.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        log.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }
}
