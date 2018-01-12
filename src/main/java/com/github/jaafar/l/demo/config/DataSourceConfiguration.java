package com.github.jaafar.l.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.jaafar.l.demo.config.datasource.DataSourceKey;
import com.github.jaafar.l.demo.config.datasource.DynamicDataSourceContextHolder;
import com.github.jaafar.l.demo.config.datasource.DynamicRoutingDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/11 15:47
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * master DataSource
     * @Primary 注解用于标识默认使用的 DataSource Bean，因为有5个 DataSource Bean，该注解可用于 master
     * 或 slave DataSource Bean, 但不能用于 dynamicDataSource Bean, 否则会产生循环调用
     *
     * @ConfigurationProperties 注解用于从 application.properties 文件中读取配置，为 Bean 设置属性
     * @return data source
     */
    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Slave alpha data source.
     *
     * @return the data source
     */
    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.master.name(), master());
        dataSourceMap.put(DataSourceKey.slave.name(), slave());

        // 将 master 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        // 将 Slave 数据源的 key 放在集合中，用于轮循
//        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
//        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.master.name());
        return dynamicRoutingDataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     *
     * @return the sql session factory bean
     */
//    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        return sqlSessionFactoryBean;
//    }

    @Value("${mybatis.mapper-locations}")
    private String xmlLocation;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource());
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Interceptor[] plugins =  new Interceptor[]{pageHelper};
        bean.setPlugins(plugins);
        bean.setMapperLocations(resolver.getResources(xmlLocation));
        return bean.getObject();
    }

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
