package com.kinglong.demo2.rest;

import com.kinglong.demo2.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sony on 2015/4/21.
 */
@Controller
@RequestMapping(value = "test")
public class DemoController {
    @Autowired
    UserDO userDO;

    @RequestMapping(value = "test")
    @ResponseBody
    public String test(HttpServletRequest request,HttpServletResponse response) {
        System.out.println(request.getAttribute("var1"));
        return null;
    }

    @RequestMapping(value = "test2",method = RequestMethod.POST)
    @ResponseBody
    public Map test2(HttpServletRequest request,HttpServletResponse response) {
        String qs = request.getQueryString();
        System.out.print(qs);
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new BufferedInputStream(request.getInputStream())));
            String line;
            while((line = bf.readLine())!=null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("var1",1);
        map.put("var2",2);
        return map;
    }
}
