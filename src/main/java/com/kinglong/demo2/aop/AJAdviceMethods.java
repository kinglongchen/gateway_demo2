package com.kinglong.demo2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sony on 2015/4/29.
 */
@Aspect
@Component
public class AJAdviceMethods {
    @Before(value = "target(com.kinglong.demo2.rest.DemoController) && execution(* test2(..))")
    public void preHandle(JoinPoint joinPoint) {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        System.out.println("测试切面通过哦！");
        request.setAttribute("var1","变量拦截测试！");
    }

}
