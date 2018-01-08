package com.github.jaafar.l.demo.entity;

import javax.persistence.*;

@Table(name = "cn_user")
public class CnUser {
    @Id
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String sex;

    private Integer age;

    private String birthday;

    private String address;

    private String mobile;

    @Column(name = "crt_time")
    private String crtTime;

    @Column(name = "crt_usr")
    private String crtUsr;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "upd_time")
    private String updTime;

    @Column(name = "upd_usr")
    private String updUsr;

    @Column(name = "upd_host")
    private String updHost;

    private String status;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return crt_time
     */
    public String getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime
     */
    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * @return crt_usr
     */
    public String getCrtUsr() {
        return crtUsr;
    }

    /**
     * @param crtUsr
     */
    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    /**
     * @return crt_host
     */
    public String getCrtHost() {
        return crtHost;
    }

    /**
     * @param crtHost
     */
    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    /**
     * @return upd_time
     */
    public String getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     */
    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    /**
     * @return upd_usr
     */
    public String getUpdUsr() {
        return updUsr;
    }

    /**
     * @param updUsr
     */
    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }

    /**
     * @return upd_host
     */
    public String getUpdHost() {
        return updHost;
    }

    /**
     * @param updHost
     */
    public void setUpdHost(String updHost) {
        this.updHost = updHost;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}