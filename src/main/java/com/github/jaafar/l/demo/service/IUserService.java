package com.github.jaafar.l.demo.service;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 10:23
 */
public interface IUserService {
    String authorize(String username, String password) throws Exception;

    boolean logout(String token, int expire) throws Exception;
}
