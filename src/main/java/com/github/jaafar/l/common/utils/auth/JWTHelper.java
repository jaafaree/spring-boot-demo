package com.github.jaafar.l.common.utils.auth;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.utils.KeyHelper;
import com.github.jaafar.l.common.utils.StringHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

/**
 * @author Yyp
 * @version 2017/10/11.
 */
@Slf4j
public class JWTHelper {

    private static KeyHelper keyHelper = new KeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, int expire, String priPath) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUniqueName())
                .claim(CommonConstants.JWT_KEY_USER_ID, jwtInfo.getId())
                .claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, keyHelper.getPrivateKey(priPath))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parseToken(String token, String pubPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(keyHelper.getPublicKey(pubPath)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubPath
     * @return
     * @throws Exception
     */
    public static JWTInfo getJWTInfoByToken(String token, String pubPath) throws Exception {
        Jws<Claims> claimsJws = parseToken(token, pubPath);
        Claims body = claimsJws.getBody();
        return new JWTInfo(StringHelper.getObjectValue(body.getSubject()),
                StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)),
                StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
    }

    /**
     * 获取token剩余过期时间（秒）
     *
     * @param token
     * @param pubPath
     * @return
     * @throws Exception
     */
    public static int getExpireByToken(String token, String pubPath) throws Exception {
        Jws<Claims> claimsJws = parseToken(token, pubPath);
        Claims body = claimsJws.getBody();
        long end = body.getExpiration().getTime();
        long now = DateTime.now().toDate().getTime();
        double a = (double) (end - now) / 1000;
        int expire = (int) Math.ceil(a);
        return expire;
    }

}
