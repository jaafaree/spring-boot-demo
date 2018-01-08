package com.github.jaafar.l.demo.config;

import com.github.jaafar.l.common.handler.GlobalExceptionHandler;
import com.github.jaafar.l.demo.interceptor.AuthorizeInterceptor;
import com.github.jaafar.l.demo.interceptor.ReqLogInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 权限拦截配置
 *
 * @author jaafaree
 * @create 2018/1/2 10:19
 */
@Configuration
public class Webconfig extends WebMvcConfigurerAdapter {

    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Value("${authorize.exclude-uris}")
    private String excludeUris;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截请求 日志记录
         * 放在权限验证拦截器前边记录所有请求
         * 存疑：服务前端要有ratelimit保护，否则容易把数据库down掉
         * */
        registry.addInterceptor(getReqLogInterceptor()).addPathPatterns("/**");
        /**
         * 权限验证拦截器
         * */
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getAuthrizeInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
    }

    @Bean
    AuthorizeInterceptor getAuthrizeInterceptor(){
        return new AuthorizeInterceptor();
    }
    @Bean
    ReqLogInterceptor getReqLogInterceptor(){
        return new ReqLogInterceptor();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = excludeUris.split(",");
        Collections.addAll(list, urls);
        return list;
    }
}
