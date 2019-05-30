package com.csii.controller.pushMsg.enums;


/**
 * 推送维度枚举
 * 
 * @author yangqun.yq
 * @version $Id: DeliveryType.java, v 0.1 2014-10-28 PM5:01:06 yangqun.yq Exp $
 */
public enum DeliveryType {

    OTHER(0, "other"),

    /**
     * 设备维度，指定安卓设备推送
     */
    DEVICE_ANDROID(1, "ByAndroid"),

    /**
     * 设备维度，指定IOS设备推送
     */
    DEVICE_IOS(2, "ByIos"),

    /**
     * 用户维度
     */
    USER(3, "ByUserId");

    private int    value;

    private String description;
    
    public static final DeliveryType valueof(int type) {
        for (DeliveryType item : values()) {
            if (type == item.value) {
                return item;
            }
        }
        return DeliveryType.OTHER;
    }


    private DeliveryType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
