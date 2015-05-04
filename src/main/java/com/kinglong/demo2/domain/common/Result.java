package com.kinglong.demo2.domain.common;


import com.kinglong.demo2.common.DemoError;
import lombok.Getter;
import lombok.Setter;

/**
 * 新版包装result
 * Created by yangrui on 14-5-28.
 */
public class Result<D> {

    @Getter
    @Setter
    private boolean success;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private D data;

    // -------------------------- STATIC METHODS --------------------------

    public static <D> Result<D> wrapSuccessfulResult(D data) {
        Result<D> result = new Result<D>();
        result.data = data;
        result.success = true;
        return result;
    }

    public static <D> Result<D> wrapErrorResult(DemoError error) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMessage();
        return result;
    }


    /**
     * 错误Message中不要补充信息时的构造函数
     * @return
     */
    public static <D> Result<D> wrapErrorResult(DemoError error,  Object... extendMsg) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = String.format(error.getMessage(), extendMsg);
        return result;
    }

    public static <D> Result<D> wrapErrorResult(String code, String message) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;

    }
}