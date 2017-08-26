package com.xingxue.class11.framework.page;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Condition {

    private String key;

    private Object param;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Condition(String key, Object param) {
        this.key = key;
        this.param = param;
    }
}
