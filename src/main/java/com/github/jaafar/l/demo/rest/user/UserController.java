package com.github.jaafar.l.demo.rest.user;

import com.github.jaafar.l.common.VO.ObjectRestResponse;
import com.github.jaafar.l.common.context.BaseContextHandler;
import com.github.jaafar.l.common.controller.BaseController;
import com.github.jaafar.l.demo.biz.CnUserBiz;
import com.github.jaafar.l.demo.entity.CnUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 16:10
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController<CnUserBiz, CnUser> {

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public ObjectRestResponse getUserExsitByUsernames(@PathVariable String username){
        String name = BaseContextHandler.getName();
        log.info("=============> 当前操作用户名称：" + name);
        return new ObjectRestResponse().rel(true).data(baseBiz.getUserExistByUsername(username));
    }
}
