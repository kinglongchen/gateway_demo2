package com.kinglong.demo2.aop;


import org.springframework.stereotype.Component;

/**
 * Created by sony on 2015/4/28.
 */
@Component
public class AdviceMethods {
    public void preHandle() {
        System.out.println("测试切面通过哦！");
    }
}
