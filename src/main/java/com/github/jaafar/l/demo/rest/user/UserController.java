package com.github.jaafar.l.demo.rest.user;

import com.github.jaafar.l.common.VO.ObjectRestResponse;
import com.github.jaafar.l.common.context.BaseContextHandler;
import com.github.jaafar.l.demo.biz.CnUserBiz;
import com.github.jaafar.l.demo.config.datasource.TargetDataSource;
import com.github.jaafar.l.demo.entity.CnUser;
import com.github.jaafar.l.demo.exs.UserInvalidException;
import com.github.jaafar.l.demo.exs.UserNotFoundException;
import com.github.jaafar.l.ratelimiter.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 16:10
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private CnUserBiz cnUserBiz;

    @Value("${redis.sysName}")
    private String sysName;
    @Value("${redis.default-expire}")
    private int defaultExpire;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @RateLimit(count = 10)
    @TargetDataSource(name = "slave")
    public ObjectRestResponse getUserExsitByUsernames(@PathVariable String username){
        String name = BaseContextHandler.getName();
        log.info("=============> 当前操作用户名称：" + name);
        return new ObjectRestResponse().rel(true).data(cnUserBiz.getUserExistByUsername(username));
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @TargetDataSource(name = "master")
    @RateLimit(count = 5)
    public ObjectRestResponse saveUser(CnUser cnUser) {
        boolean userExist = cnUserBiz.getUserExistByUsername(cnUser.getUsername());
        if (userExist) {
            throw new UserInvalidException("User already exist!");
        }
        cnUser.setId(null);
        cnUser.setCrtTime(new DateTime().toString("yyyyMMdd HH:mm:ss"));
        cnUser.setStatus("1");
        cnUserBiz.insertUser(cnUser.getUsername(), defaultExpire, cnUser);
        return new ObjectRestResponse().rel(true).data(true);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @TargetDataSource(name = "master")
    @RateLimit(count = 5)
    public ObjectRestResponse updateuser(CnUser cnUser) {
        CnUser oldCnUser = null;
        oldCnUser = cnUserBiz.selectById(cnUser.getId());
        if (oldCnUser == null) {
            throw new UserNotFoundException("User not found!");
        }
        cnUser.setUsername(oldCnUser.getUsername());
        cnUser.setUpdTime(new DateTime().toString("yyyyMMdd HH:mm:ss"));
        cnUserBiz.updateUser(cnUser);
        return new ObjectRestResponse().rel(true).data(true);
    }
}
