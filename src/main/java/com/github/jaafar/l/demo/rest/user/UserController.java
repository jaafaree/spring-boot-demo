package com.github.jaafar.l.demo.rest.user;

import com.github.jaafar.l.common.VO.ObjectRestResponse;
import com.github.jaafar.l.common.context.BaseContextHandler;
import com.github.jaafar.l.demo.biz.CnUserBiz;
import com.github.jaafar.l.ratelimiter.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @RateLimit(count = 10)
    public ObjectRestResponse getUserExsitByUsernames(@PathVariable String username){
        String name = BaseContextHandler.getName();
        log.info("=============> 当前操作用户名称：" + name);
        return new ObjectRestResponse().rel(true).data(cnUserBiz.getUserExistByUsername(username));
    }
}
