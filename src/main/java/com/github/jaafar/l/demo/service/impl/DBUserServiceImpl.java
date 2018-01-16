package com.github.jaafar.l.demo.service.impl;

import com.ace.cache.annotation.Cache;
import com.ace.cache.api.impl.CacheRedis;
import com.alibaba.fastjson.JSON;
import com.github.jaafar.l.common.utils.auth.JWTInfo;
import com.github.jaafar.l.demo.biz.CnUserBiz;
import com.github.jaafar.l.demo.entity.CnUser;
import com.github.jaafar.l.demo.exs.UserInvalidException;
import com.github.jaafar.l.demo.exs.UserNotFoundException;
import com.github.jaafar.l.demo.service.IUserService;
import com.github.jaafar.l.demo.utils.AuthorizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 10:25
 */
@Slf4j
@Service
public class DBUserServiceImpl implements IUserService {
    private AuthorizeUtil authorizeUtil;
    private CnUserBiz cnUserBiz;
    private CacheRedis cacheRedis;

    @Value("${redis.sysName}")
    private String sysName;
    @Value("${redis.default-expire}")
    private int defaultExpire;

    @Autowired
    public DBUserServiceImpl(AuthorizeUtil authorizeUtil, CnUserBiz cnUserBiz, CacheRedis cacheRedis) {
        this.authorizeUtil = authorizeUtil;
        this.cnUserBiz = cnUserBiz;
        this.cacheRedis = cacheRedis;
    }

    @Override
    public String authorize(String username, String password) throws Exception {
        String cache = cacheRedis.get(sysName + ":users:" + username);
        CnUser cnUser = null;
        if (cache == null || cache.length() == 0){
            cnUser = cnUserBiz.getCnUserByUsername(username, defaultExpire);
            log.info(" Select user from database...");
        } else {
            cnUser = JSON.parseObject(cache, CnUser.class);
            log.info(" Select user from cache...");
        }
        if (cnUser == null){
            throw new UserNotFoundException("User:" + username + " Not Found!");
        }
        if (!"1".equals(cnUser.getStatus())){
            throw new UserNotFoundException("User:" + username + " is Frozen!");
        }
        if (!cnUser.getPassword().equals(password)){
            throw new UserInvalidException("User password error!");
        }
        String token = authorizeUtil.generateToken(new JWTInfo(cnUser.getUsername(),cnUser.getId().toString(),cnUser.getName()));
        return token;
    }

    @Override
    @Cache(key = "tokens{1}", expireExpression = "{2}" )
    public boolean logout(String token, int expire) throws Exception {

        return true;
    }
}
