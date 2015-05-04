package com.kinglong.demo2.forward.handler.common;

import com.kinglong.demo2.forward.handler.ForwardHandler;
import lombok.Data;

/**
 * Created by sony on 2015/4/30.
 */
@Data
public class ForwardHandlerHolder<O> {
    private String srcUrl;
    private String desUrl;
    private O obj;
    private ForwardHandler handler;

    public ForwardHandlerHolder() {}

    public ForwardHandlerHolder(String srcUrl, String desUrl, O obj,ForwardHandler handler) {
        this.srcUrl = srcUrl;
        this.desUrl = desUrl;
        this.obj = obj;
        this.handler = handler;
    }
}
