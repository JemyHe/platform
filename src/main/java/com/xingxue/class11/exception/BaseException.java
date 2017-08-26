package com.xingxue.class11.exception;

/**
 * Created by Administrator on 2017/6/30.
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
