package com.github.jaafar.l.demo.service.impl;

import com.ace.cache.annotation.Cache;
import com.github.jaafar.l.demo.utils.AuthorizeUtil;
import com.github.jaafar.l.common.utils.auth.JWTInfo;
import com.github.jaafar.l.demo.biz.CnUserBiz;
import com.github.jaafar.l.demo.entity.CnUser;
import com.github.jaafar.l.demo.exs.UserInvalidException;
import com.github.jaafar.l.demo.exs.UserNotFoundException;
import com.github.jaafar.l.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public DBUserServiceImpl(AuthorizeUtil authorizeUtil, CnUserBiz cnUserBiz) {
        this.authorizeUtil = authorizeUtil;
        this.cnUserBiz = cnUserBiz;
    }

    @Override
    public String authorize(String username, String password) throws Exception {
        CnUser cnUser = null;
        cnUser = cnUserBiz.getCnUserByUsername(username);
        if (cnUser == null){
            throw new UserNotFoundException("User:" + username + " Not Found!");
        }
        if (!"1".equals(cnUser.getStatus())){
            //throw new UserNotFoundException("User:" + username + " Not Found!");
            throw new UserNotFoundException("User:" + username + " is Frozen!");
        }
        if (!cnUser.getPassword().equals(password)){
            throw new UserInvalidException("User password error!");
        }
        //log.info(BaseContextHandler.toString());
        String token = authorizeUtil.generateToken(new JWTInfo(cnUser.getUsername(),cnUser.getId().toString(),cnUser.getName()));
        return token;
    }

    @Override
    @Cache(key = "tokens{1}", expireExpression = "{2}" )
    public boolean logout(String token, int expire) throws Exception {

        return true;
    }
}
