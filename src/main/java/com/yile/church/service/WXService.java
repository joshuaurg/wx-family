package com.yile.church.service;

import com.yile.church.common.engine.VoiceEngine;
import com.yile.church.common.model.ProcessorContext;
import com.yile.church.common.processor.AbstractProcessor;
import com.yile.church.model.resp.TextMessage;
import com.yile.church.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author : hema
 * @date : 2017年01月19日 下午5:49
 */
@Service
public class WXService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXService.class);

    @Autowired
    private VoiceEngine voiceEngine;

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "试试语音，更快捷！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                if(requestMap.containsKey("Recognition")){
                    ProcessorContext context = new ProcessorContext();
                    context.setResource(requestMap.get("Recognition"));
                    context = processVoice(context);
                    respContent = context.getMsg();
                }
            }
            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {

            LOGGER.error("process message error.",e);
        }
        return respMessage;
    }

    private ProcessorContext processVoice(ProcessorContext context) {
        LOGGER.info(voiceEngine.hashCode()+"");
        context = voiceEngine.getProcessor(context);
        if(context.getProcessor() == null){
            context.setMsg("不知所云...");
        }
        voiceEngine.processStart(context);
        return context;
    }
}
