package com.github.jaafar.l.demo.utils;

import com.ace.cache.service.ICacheManager;
import com.github.jaafar.l.demo.exs.jwt.JwtIllegalArgumentException;
import com.github.jaafar.l.demo.exs.jwt.JwtSignatureException;
import com.github.jaafar.l.demo.exs.jwt.JwtTokenExpiredException;
import com.github.jaafar.l.common.utils.auth.IJWTInfo;
import com.github.jaafar.l.common.utils.auth.JWTHelper;
import com.github.jaafar.l.demo.exs.TokenLogoutException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 13:40
 */
@Configuration
public class AuthorizeUtil {
    @Value("${authorize.pri-key.path}")
    private String priKeyPath;
    @Value("${authorize.pub-key.path}")
    private String pubKeyPath;
    @Value("${authorize.expire}")
    private int expire;
    @Value("${redis.sysName}")
    private String sysName;

    @Autowired
    private ICacheManager cacheManager;//ace-cache中的redis交互接口

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, expire, priKeyPath);
    }

    public IJWTInfo getInfoByToken(String token) throws Exception {
        try {
            return JWTHelper.getJWTInfoByToken(token, pubKeyPath);
        }catch (ExpiredJwtException ex){
            throw new JwtTokenExpiredException("User token expired!");
        }catch (SignatureException ex){
            throw new JwtSignatureException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new JwtIllegalArgumentException("User token is null or empty!");
        }
    }

    public void getRedisCacheByToken(String token){
        StringBuffer relkey = new StringBuffer();
        relkey.append(sysName).append(":tokens:").append(token);
        String val = cacheManager.get(relkey.toString());
        if ("true".equals(val)){
            throw new TokenLogoutException("User token logout!");
        }
    }

    public int getExpireMins(String token) throws Exception {
        int expireSecs = JWTHelper.getExpireByToken(token, pubKeyPath);
        double expireMins = expireSecs / 60;
        return (int) Math.ceil(expireMins);
    }

}
