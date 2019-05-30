package com.csii.controller.pushMsg.model;

import java.util.Map;

/**
 * 模版推送请求类，适合场景单推，也允许指定若干对象推送
 * @author wangguang.wg
 * @date 15/5/9
 */
public class PushTemplateReq extends BaseParam {

    /**
     * 任务名称
     */
    private String             taskName;

    /**
     * 模版名称
     */
    private String             templateName;

    /**
     * 模版中设置变量K,V
     */
    private Map<String,String> templatekv;

    /**
     * 过期时间
     */
    private long               expiredSeconds;

    /**
     * 推送维度，参照DeliveryType枚举
     */
    private int                deliveryType;

    /**
     * K:设备维度推送时，该值为设备标识；用户维度推送时，该值为用户标识
     * V:业务消息ID，用于追踪消息状态，该值会下发到客户端，为方便排查问题，请保证该值的唯一性
     * 模版推送可以指定若干用户或者设备，但是一般只用作单发场景
     */
    private Map<String,String> target_msgkey;

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

    public long getExpiredSeconds() {
        return expiredSeconds;
    }

    public void setExpiredSeconds(long expiredSeconds) {
        this.expiredSeconds = expiredSeconds;
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

    public Map<String, String> getTarget_msgkey() {
        return target_msgkey;
    }

    public void setTarget_msgkey(Map<String, String> target_msgkey) {
        this.target_msgkey = target_msgkey;
    }
}
