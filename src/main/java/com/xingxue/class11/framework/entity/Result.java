package com.xingxue.class11.framework.entity;

/**
 * Created by Administrator on 2017/7/1.
 *
 * dto--->给前台工程师看的----》请求成功 ----》实体信息
 *                         ---》请求失败----》错误的信息
 *
 *               List<User>
 *               Result<User>
 */
public class Result<T> {

    //请求是否成功
    private boolean success;

    //错误信息
    private String error;

    //成功信息
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
