package com.xingxue.class11.entity;


import com.xingxue.class11.framework.entity.BaseEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/28.
 */
public class User extends BaseEntity {

    private String password;

    private String name;

    private Date loginTime;

    private Date createTime;

    private Date updateTime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", loginTime=" + loginTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }


}
