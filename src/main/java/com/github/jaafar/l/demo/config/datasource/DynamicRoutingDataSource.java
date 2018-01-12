package com.github.jaafar.l.demo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/11 15:45
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = DynamicDataSourceContextHolder.getDataSourceKey();
        log.info("current data source is: {}", dataSourceKey);
        return dataSourceKey;
    }
}
