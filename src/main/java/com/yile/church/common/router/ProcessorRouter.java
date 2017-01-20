package com.yile.church.common.router;

import com.yile.church.common.processor.AbstractProcessor;
import java.util.Map;

/**
 * @author : hema
 * @date : 2017年01月19日 下午6:36
 */
public class ProcessorRouter  {


    private Map<String,AbstractProcessor> processors;

    public Map<String, AbstractProcessor> getProcessors() {
        return processors;
    }

    public void setProcessors(Map<String, AbstractProcessor> processors) {
        this.processors = processors;
    }

    public AbstractProcessor route(String info) {
        for(Map.Entry<String,AbstractProcessor> entry : processors.entrySet()){
            if(info.contains(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }
}
