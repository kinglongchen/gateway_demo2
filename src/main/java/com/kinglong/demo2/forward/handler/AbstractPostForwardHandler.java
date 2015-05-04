package com.kinglong.demo2.forward.handler;

import com.kinglong.demo2.forward.handler.ForwardHandlerManager;
import com.kinglong.demo2.forward.handler.PostForwardHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sony on 2015/4/30.
 */
public abstract class AbstractPostForwardHandler<BO> implements PostForwardHandler{
    private BO bo;
    @Autowired
    public ForwardHandlerManager forwardHandlerManager;

    public void setBo(BO bo) {
        this.bo = bo;
    }


}
