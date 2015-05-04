package com.kinglong.demo2.forward.handler;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sony on 2015/4/30.
 */
public abstract class AbstractPreForwardHandler implements PreForwardHandler{
    @Autowired
    public ForwardHandlerManager forwardHandlerManager;
}
