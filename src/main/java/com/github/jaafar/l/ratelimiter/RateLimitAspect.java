package com.github.jaafar.l.ratelimiter;

import com.ace.cache.api.CacheAPI;
import com.ace.cache.service.ICacheManager;
import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/10 12:18
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {
    @Value("${redis.sysName}")
    private String sysName;
    @Autowired
    private CacheAPI cacheAPI;//write
    @Autowired
    private ICacheManager cacheManager;//read

    @Pointcut("@annotation(com.github.jaafar.l.ratelimiter.RateLimit)")
    public void aspect() {
    }

    @Before("aspect()&&@annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RateLimit limit) {
        Object currentUserIP = BaseContextHandler.get(CommonConstants.CONTEXT_USER_IP);
        Object currentUserUri = BaseContextHandler.get(CommonConstants.CONTEXT_USER_URI);
        if (currentUserIP == null) {
            throw new RateLimitException("获取参数：IP失败");
        }
        if (currentUserUri == null) {
            throw new RateLimitException("获取参数：URI失败");
        }
        String ip = currentUserIP.toString();
        String url = currentUserUri.toString();
        String key = sysName + ":ratelimits:"
                .concat(url.replaceAll("\\/", "y"))
                .concat(ip.replaceAll(":", "z"));
        if(cacheManager.get(key)==null){
            cacheAPI.set(key, 1, limit.time());
            log.info("------------------" + key + "已缓存----------------");
        }else{
             int val = Integer.valueOf(cacheManager.get(key)) + 1;
            cacheAPI.remove(key);
            cacheAPI.set(key, val, limit.time());
            log.info("------------------" + key + "已更新----------------");
        }
        int count = Integer.valueOf(cacheManager.get(key));
        if (count > limit.count()) {
            log.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
            throw new RateLimitException("访问过于频繁，请稍后重试。");
        }
    }
}
