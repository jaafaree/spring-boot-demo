package com.github.jaafar.l.demo.init;

import com.ace.cache.api.impl.CacheRedis;
import com.github.jaafar.l.demo.biz.CnUserBiz;
import com.github.jaafar.l.demo.entity.CnUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 启动时加载系统用户到Redis缓存
 *
 * Spring Boot 提供CommandLineRunner接口
 * Spring Boot 应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。
 * 也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。
 *
 * @author jaafaree
 * @create 2018/1/15 11:51
 */
@Slf4j
@Component
@Order(10)
public class ApplicationInit implements CommandLineRunner {
    @Value("${redis.sysName}")
    private String sysName;
    @Value("${redis.default-expire}")
    private int defaultExpire;
    @Autowired
    private CnUserBiz cnUserBiz;
    @Autowired
    private CacheRedis cacheRedis;
    @Override
    public void run(String... strings) throws Exception {
        cacheRedis.removeByPre(sysName);
        List<CnUser> cnUsers = cnUserBiz.selectListAll();
        for (CnUser cnUser : cnUsers){
            cacheRedis.set(sysName + ":users:" + cnUser.getUsername(), cnUser, defaultExpire);
        }
        log.info("======== Users cached success! ========");
    }
}
