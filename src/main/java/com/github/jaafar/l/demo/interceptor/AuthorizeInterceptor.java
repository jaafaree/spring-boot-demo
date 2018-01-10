package com.github.jaafar.l.demo.interceptor;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.context.BaseContextHandler;
import com.github.jaafar.l.common.utils.auth.IJWTInfo;
import com.github.jaafar.l.demo.utils.AuthorizeUtil;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 10:13
 */
@Slf4j
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {
    @Value("${authorize.token-header}")
    private String hearder;
    @Autowired
    private AuthorizeUtil authorizeUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre interceptor...");
        String token = request.getHeader(hearder);
        IJWTInfo infoFromToken = authorizeUtil.getInfoByToken(token);//token校验
        authorizeUtil.getRedisCacheByToken(token);//验证token是否缓存
        BaseContextHandler.setUserName(infoFromToken.getUniqueName());
        BaseContextHandler.setName(infoFromToken.getName());
        BaseContextHandler.setUserId(infoFromToken.getId());
        BaseContextHandler.setToken(token);
        BaseContextHandler.set(CommonConstants.CONTEXT_USER_IP, getRemoteAddr(request));
        BaseContextHandler.set(CommonConstants.CONTEXT_USER_URI, request.getRequestURI());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    private String getRemoteAddr(final HttpServletRequest request) {
        if (request.getHeader(HttpHeaders.X_FORWARDED_FOR) != null) {
            return request.getHeader(HttpHeaders.X_FORWARDED_FOR);
        }
        return request.getRemoteAddr();
    }
}
