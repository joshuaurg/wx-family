package com.yile.church.common.processor;

import com.yile.church.common.model.ProcessorContext;

import java.io.UnsupportedEncodingException;

/**
 * Created by dcx on 17/1/20.
 */
public interface AbstractProcessor {

    void process(ProcessorContext context) throws UnsupportedEncodingException, Exception;
}
