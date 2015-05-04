package com.kinglong.demo2.filter;

import com.kinglong.demo2.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by sony on 2015/4/29.
 */
@Component
@Scope
public class DemoFilter implements Filter {
    @Autowired
    UserDO userDO;

    public static Boolean isInit = Boolean.FALSE;

    private void DemoFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------------Filter初始化-------------");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("前----执行Filter");
        if(userDO.getName()!=null) {
            System.out.println("------------------------");
            System.out.println(userDO.getName());
            System.out.println("------------------------");
        } else {
            userDO.setName("kinlong###");
        }
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("后----执行Filter");

    }

    @Override
    public void destroy() {

    }
}
