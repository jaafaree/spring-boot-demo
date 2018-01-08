package com.github.jaafar.l.common.utils.auth;

import java.io.Serializable;

/**
 * @author Yyp
 * @version 2017/10/12.
 */
public class JWTInfo implements Serializable, IJWTInfo {
    private String userName;
    private String userId;
    private String name;

    public JWTInfo(String userName, String userId, String name) {
        this.userName = userName;
        this.userId = userId;
        this.name = name;
    }

    public String getUniqueName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JWTInfo jwtInfo = (JWTInfo) o;

        if (!userId.equals(jwtInfo.userId)) return false;
        if (!userName.equals(jwtInfo.userName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }
}
