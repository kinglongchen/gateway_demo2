package com.kinglong.demo2.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sony on 2015/4/29.
 */
public enum DemoError {
    CHCHAR_TEST_ERROR("1","测试中文错误！"),
    ENCHAR_TEST_ERROR("2","English Char Error");

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String message;

    private DemoError(String code,String message) {
        this.code = code;
        this.message = message;
    }



}
