package com.github.jaafar.l.demo.entity;

import javax.persistence.*;

@Table(name = "req_log")
public class ReqLog {
    @Id
    private Integer id;

    @Column(name = "client_ip")
    private String clientIp;

    private String type;

    private String method;

    private String time;

    @Column(name = "return_time")
    private String returnTime;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "time_consuming")
    private Integer timeConsuming;

    private String uri;

    @Column(name = "param_data")
    private String paramData;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "return_data")
    private String returnData;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return client_ip
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return return_time
     */
    public String getReturnTime() {
        return returnTime;
    }

    /**
     * @param returnTime
     */
    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * @return status_code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return time_consuming
     */
    public Integer getTimeConsuming() {
        return timeConsuming;
    }

    /**
     * @param timeConsuming
     */
    public void setTimeConsuming(Integer timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    /**
     * @return uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return param_data
     */
    public String getParamData() {
        return paramData;
    }

    /**
     * @param paramData
     */
    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    /**
     * @return session_id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return return_data
     */
    public String getReturnData() {
        return returnData;
    }

    /**
     * @param returnData
     */
    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }
}