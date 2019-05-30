package com.csii.controller.pushMsg.model;

import java.util.Map;


/** 简单推送请求类
 * @author wangguang.wg
 */
public class PushSimpleReq extends BaseParam {

    /**
     * 任务名称
     */
    private String             taskName;

    /**
     * 消息推送标题
     */
    private String             title;

    /**
     * 消息推送内容
     */
    private String             content;

    /**
     * 消息推送跳转地址
     */
    private String             uri;

    /**
     * 推送维度，参考DeliveryType枚举
     */
    private int                deliveryType;

    /**
     * 消息有效期，单位s，消息有效期内设备在线方可下发，请根据实际业务场景设置，最长不能超过7天
     */
    private long               expiredSeconds;

    /**
     * K:设备维度推送时，该值为设备标识；用户维度推送时，该值为用户标识
     * V:业务消息ID，用于追踪消息状态，该值会下发到客户端，为方便排查问题，请保证该值的唯一性
     * 简单推送可以指定若干用户或者设备，但是一般只用作单发场景
     */
    private Map<String,String> target_msgkey;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Map<String, String> getTarget_msgkey() {
        return target_msgkey;
    }

    public void setTarget_msgkey(Map<String, String> target_msgkey) {
        this.target_msgkey = target_msgkey;
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

}
