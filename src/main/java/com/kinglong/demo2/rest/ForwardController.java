package com.kinglong.demo2.rest;

import com.kinglong.demo2.util.ForwardUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sony on 2015/4/29.
 */
@Controller
@RequestMapping("v2.0/**")
public class ForwardController {
    @RequestMapping
    public void forward(HttpServletRequest request,HttpServletResponse response) {
        ForwardUtil.doForward(request,response);
    }
}
