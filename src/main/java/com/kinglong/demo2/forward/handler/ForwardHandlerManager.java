package com.kinglong.demo2.forward.handler;

import com.kinglong.demo2.exception.ForwardHandlerException;
import com.kinglong.demo2.forward.handler.common.ForwardHandlerHolder;
import com.kinglong.demo2.util.ForwardUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sony on 2015/4/30.
 */
@Component
@Scope
public class ForwardHandlerManager {
    private Map<String,ForwardHandlerHolder> preHandlerMap = new HashMap<String, ForwardHandlerHolder>();
    private Map<String,ForwardHandlerHolder> postHandlerMap = new HashMap<String, ForwardHandlerHolder>();
    public PreForwardHandler getPreHandler(String url) {
        return (PreForwardHandler) preHandlerMap.get(url.trim()).getHandler();
    }
    public PostForwardHandler getPosthandler(String url) {
        return (PostForwardHandler) postHandlerMap.get(url.trim()).getHandler();
    }

    public <BO> void addPreForwardHandler(String srcUrl,String desUrl,Class<BO> clazz,PreForwardHandler handler) throws ForwardHandlerException {
        srcUrl = srcUrl.trim();
        desUrl = desUrl.trim();
        if (preHandlerMap.get(srcUrl)!=null) {
            //TODO 记录替换前置处理器的log
        }
        if (desUrl == null) {
            desUrl = srcUrl;
        }
        ForwardHandlerHolder holder = new ForwardHandlerHolder(srcUrl,desUrl,clazz,handler);
        preHandlerMap.put(srcUrl,holder);
    }

    public <DO> void addPostForwardHandler(String srcUrl,Class<DO> obj,PostForwardHandler handler) {
        srcUrl = srcUrl.trim();
        if (postHandlerMap.get(srcUrl)!=null) {
            //TODO 记录将要覆盖后置处理器的log
        }
        ForwardHandlerHolder holder = new ForwardHandlerHolder(srcUrl,null,obj,handler);
        postHandlerMap.put(srcUrl,holder);
    }

    public void preHandle(HttpServletRequest request,HttpServletResponse response) throws ForwardHandlerException {
        getPreHandler(ForwardUtil.getRequestPath(request)).preHandle(request, response);
    }

    public void postHandle(HttpServletRequest request,HttpServletResponse response) {
        getPosthandler(ForwardUtil.getRequestPath(request)).postHandle(request,response);
    }
}
