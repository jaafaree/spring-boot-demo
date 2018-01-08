package com.github.jaafar.l.common.VO;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 10:31
 */
public class BaseResponse {
    private int status = 200;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
