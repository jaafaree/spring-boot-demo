package com.github.jaafar.l.demo.rest.user;

import com.github.jaafar.l.common.VO.ObjectRestResponse;
import com.github.jaafar.l.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 10:17
 */
@Slf4j
@RestController
@RequestMapping("login")
public class UserLoginController {
    @Autowired
    private IUserService iUserService;
    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public ObjectRestResponse login(@RequestParam String username, @RequestParam String password) throws Exception {
        String token = iUserService.authorize(username, password);
        return new ObjectRestResponse().rel(true).data(token);
    }


}
