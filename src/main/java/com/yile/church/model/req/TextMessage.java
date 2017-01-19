package com.yile.church.model.req;

/**
 * @author : hema
 * @date : 2017年01月19日 下午5:46
 */
public class TextMessage extends BaseMessage {

    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
