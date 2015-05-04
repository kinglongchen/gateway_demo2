package com.kinglong.demo2.forward.handler;

/**
 * Created by sony on 2015/4/30.
 */
public interface ForwardHandler {
    public <BO> void invoke(BO bo);
}
