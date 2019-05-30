package com.csii.controller.pushMsg.model;

import java.util.Map;

/**
 * 群发请求类
 * @author wanguang.wg
 * @date 15/5/9
 */
public class PushBroadcastReq extends BaseParam {

    /**
     * 任务名称
     */
    private String             taskName;

    /**
     * 模版名称
     */
    private String             templateName;

    /**
     * 模版枚举值
     */
    private Map<String,String> templatekv;

    /**
     * 消息ID
     */
    private String             msgkey;

    /**
     * 过期时间
     */
    private long               expiredSeconds;

    /**
     * 推送维度
     */
    private int                deliveryType;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, String> getTemplatekv() {
        return templatekv;
    }

    public void setTemplatekv(Map<String, String> templatekv) {
        this.templatekv = templatekv;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getExpiredSeconds() {
        return expiredSeconds;
    }

    public void setExpiredSeconds(long expiredSeconds) {
        this.expiredSeconds = expiredSeconds;
    }

    public String getMsgkey() {
        return msgkey;
    }

    public void setMsgkey(String msgkey) {
        this.msgkey = msgkey;
    }
}
