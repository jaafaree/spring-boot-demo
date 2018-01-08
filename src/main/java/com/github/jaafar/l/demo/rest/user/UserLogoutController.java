package com.github.jaafar.l.demo.rest.user;

import com.github.jaafar.l.common.VO.ObjectRestResponse;
import com.github.jaafar.l.common.context.BaseContextHandler;
import com.github.jaafar.l.demo.service.IUserService;
import com.github.jaafar.l.demo.utils.AuthorizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/5 16:26
 */
@Slf4j
@RestController
@RequestMapping("logout")
public class UserLogoutController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private AuthorizeUtil authorizeUtil;
    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public ObjectRestResponse logout() throws Exception {
        String token = BaseContextHandler.getToken();
        int expireMins = authorizeUtil.getExpireMins(token);
        boolean success = iUserService.logout(token, expireMins);
        return new ObjectRestResponse().rel(true).data(success);
    }
}
