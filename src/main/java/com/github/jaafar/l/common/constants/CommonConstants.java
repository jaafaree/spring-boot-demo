package com.github.jaafar.l.common.constants;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 13:51
 */
public class CommonConstants {
    public static final Integer EX_TOKEN_ERROR_CODE = 40101;
    public static final Integer EX_TOKEN_ILLEGAL_CODE = 40102;
    public static final Integer EX_TOKEN_SIGN_CODE = 40103;
    public static final Integer EX_TOKEN_EXPIRE_CODE = 40104;
    public static final Integer EX_TOKEN_LOGOUT_CODE = 40105;


    public static final Integer EX_USER_INVALID_CODE = 40202;
    public static final Integer EX_USER_NOTFOUND_CODE = 40203;

    public static final Integer EX_RATELIMIT_CODE = 20101;

    public static final Integer EX_OTHER_ERROR_CODE = 500;


    public static final String LOG_SEND_TIME = "_send_time";
    public static final String LOG_SEND_ENTITY = "_send_entity";
    public static final String LOG_RETURN = "_logger_return";
    public static final String DATE_TIME_PATTEN = "yyyy/MM/dd hh:mm:ss.SSSa";

    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "username";
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USER_NAME = "currentUsername";
    public static final String CONTEXT_KEY_NAME = "currentName";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String CONTEXT_USER_IP = "currentUserIP";
    public static final String CONTEXT_USER_URI = "currentUserURI";
}
