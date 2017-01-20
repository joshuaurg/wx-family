package com.yile.church.common.model;

import com.yile.church.common.processor.AbstractProcessor;

/**
 * Created by dcx on 17/1/20.
 */
public class ProcessorContext {

    private String resource;
    private AbstractProcessor processor;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public AbstractProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(AbstractProcessor processor) {
        this.processor = processor;
    }
}
