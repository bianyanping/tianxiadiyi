package com.csii.controller.pushMsg.model;

import java.util.Map;

/**
 * 批量推送请求类
 * @author wangguang.wg
 * @date 15/5/9
 */
public class PushMultipleReq extends BaseParam {

    /**
     * 任务名称
     */
    private String                    taskName;

    /**
     * 模版名称
     */
    private String                    templateName;

    /**
     * 过期时间
     */
    private long                      expiredSeconds;

    /**
     * 推送维度
     */
    private int                       deliveryType;

    /**
     * K:设备维度推送时，该值为设备标识；用户维度推送时，该值为用户标识
     * V:业务消息详情，用于追踪消息状态，该值会下发到客户端，为方便排查问题，请保证该值的唯一性
     * 批量推送可以指定大量用户或者设备，推荐单次不超过400
     */
    private Map<String,TargetMsgInfo> target_msginfo;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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

    public Map<String, TargetMsgInfo> getTarget_msginfo() {
        return target_msginfo;
    }

    public void setTarget_msginfo(Map<String, TargetMsgInfo> target_msginfo) {
        this.target_msginfo = target_msginfo;
    }
}

