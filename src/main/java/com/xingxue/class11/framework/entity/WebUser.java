package com.xingxue.class11.framework.entity;

/**
 * Created by Administrator on 2017/7/1.
 *
 * dto ---》给前台工程师看的---》满足前台的需要
 *
 */
public class WebUser{

    private String id;
    private String username;

    public WebUser() {
    }

    public WebUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public WebUser(String username) {
        this.username = username;
    }
}
