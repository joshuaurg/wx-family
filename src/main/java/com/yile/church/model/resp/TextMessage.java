package com.yile.church.model.resp;

/**
 * @author : hema
 * @date : 2017年01月19日 下午6:08
 */
public class TextMessage extends BaseMessage{

    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
