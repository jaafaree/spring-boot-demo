package com.github.jaafar.l.common.context;

import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.utils.StringHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * 全局当前操作信息相关类  -> ThreadLocal
 * @author Yyp
 * @version 2017/10/11.
 */
public class BaseContextHandler {
    public static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, String value){
        Map<String, Object> map = threadLocal.get();
        if (map == null){
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null){
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    /*public static void init() {
        Map<String, Object> map = threadLocal.get();
        if (map == null){
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
    }*/

    public static String getUserId(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return StringHelper.getObjectValue(value);
    }

    public static String getUserName(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getName(){
        Object value = get(CommonConstants.CONTEXT_KEY_NAME);
        return StringHelper.getObjectValue(value);
    }


    public static void setUserId(String userId){
        set(CommonConstants.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setUserName(String userName){
        set(CommonConstants.CONTEXT_KEY_USER_NAME, userName);
    }

    public static void setName(String name){
        set(CommonConstants.CONTEXT_KEY_NAME, name);
    }

    public static String getToken(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }
    public static void setToken(String token){set(CommonConstants.CONTEXT_KEY_USER_TOKEN,token);}


    public static void remove(){
        threadLocal.remove();
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class UnitTest {
        private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UnitTest.class);
        @Test
        public void testSetContextVariable() throws InterruptedException {
            BaseContextHandler.set("test", "main");
            new Thread(()->{
                BaseContextHandler.set("test", "moo1");
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assertEquals(BaseContextHandler.get("test"), "moo1");
                logger.info("Thread 1 done!");
            }).start();

            new Thread(()->{
                BaseContextHandler.set("test", "moo2");
                assertEquals(BaseContextHandler.get("test"), "moo2");
                logger.info("Thread 2 done!");

            }).start();

            Thread.currentThread().sleep(5000);

            assertEquals(BaseContextHandler.get("test"), "main");
            logger.info("Thread main done!");


        }

        @Test
        public void testSetUserInfo(){
            BaseContextHandler.setUserId("test");
            assertEquals(BaseContextHandler.getUserId(), "test");

            BaseContextHandler.setUserName("test");
            assertEquals(BaseContextHandler.getUserName(), "test");
        }

    }



}
