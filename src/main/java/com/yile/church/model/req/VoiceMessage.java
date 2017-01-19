package com.yile.church.model.req;

/**
 * @author : hema
 * @date : 2017年01月19日 下午5:41
 */
public class VoiceMessage extends BaseMessage{
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
