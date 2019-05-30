/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.csii.controller.pushMsg.enums;

/** 消息类型
 * @author wangguang.wg
 * @version $Id: ExtendedParamEnum.java, v 0.1 2017-10-12 上午11:26 wangguang.wg Exp $$
 */
public enum NotifyType {

    /**
     * 通知栏消息
     */
    NOTIFY("notify"),

    /**
     * 透明消息
     */
    TRANSPARENT("transparent"),


    UNKNOWN("unknown");

    private String type;

    private NotifyType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static NotifyType getValueOf(String type) {
        for (NotifyType item : values()) {
            if (item.type.equalsIgnoreCase(type)) {
                return item;
            }
        }
        return NotifyType.UNKNOWN;
    }
}