package com.kinglong.demo2.exception;

/**
 * Created by sony on 2015/4/30.
 */
public class ForwardHandlerException extends Throwable {
    public String msg;
    public ForwardHandlerException(String msg) {
        this.msg = msg;
    }
}
