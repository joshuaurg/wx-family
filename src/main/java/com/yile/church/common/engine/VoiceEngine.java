package com.yile.church.common.engine;

import com.yile.church.common.model.ProcessorContext;
import com.yile.church.common.processor.AbstractProcessor;
import com.yile.church.common.router.ProcessorRouter;
import com.yile.church.service.WXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dcx on 17/1/20.
 */
public class VoiceEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceEngine.class);

    private ProcessorRouter processorRouter;

    public ProcessorRouter getProcessorRouter() {
        return processorRouter;
    }

    public void setProcessorRouter(ProcessorRouter processorRouter) {
        this.processorRouter = processorRouter;
    }

    public ProcessorContext getProcessor(ProcessorContext context) {
        context.setProcessor(processorRouter.route(context.getResource()));
        return context;
    }

    public void processStart(ProcessorContext context) {
        LOGGER.info("start process...");
        AbstractProcessor processor = context.getProcessor();
        processor.process(context);
    }
}
