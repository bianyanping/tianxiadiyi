/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.csii.controller.pushMsg.enums;

/**
 * 推送接口结果码枚举
 * 
 * @author yangqun.yq
 * @version $Id: PushResultEnum.java, v 0.1 2014-10-30 PM3:17:51 yangqun.yq Exp $
 */
public enum PushResultEnum {

    SUCCESS("100", "SUCCESS", "success"),

    NEED_DELIVERYTOKEN("3001", "NEED_DELIVERYTOKEN", "need token"),
    NEED_FILE("3002", "NEED_FILE", "need batch file"),
    NEED_APPID("3003", "NEED_APPID", "need appid"),
    TIMEOUT_ARG_ERROR("3004", "TIMEOUT_ARG_ERROR", "'timeout' is error"),
    APISECRET_WRONG("3005", "APISECRET_WRONG", "error secret"),
    NEED_ORDER("3006", "NEED_ORDER", "need order"),
    APPID_WRONG("3007", "APPID_WRONG", "wrong appid"),
    OS_TYPE_NOT_SUPPORTED("3008", "OS_TYPE_NOT_SUPPORTED", "ostype not support"),
    DELIVERY_TYPE_NOT_SUPPORTED("3009", "DELIVERY_TYPE_NOT_SUPPORTED", "delivery not support"),
    THIRD_MSG_ID_WRONG("3010", "THIRD_MSG_ID_WRONG", "third msgkey is error"),
    PAYLOAD_OVERLENGTH("3011", "PAYLOAD_OVERLENGTH", "length of payload error"),
    NEED_USERID("3012", "NEED_USERID", "need userid"),
    CONNECTION_STATUS_TYPE_NOT_SUPPORTED("3013", "CONNECTION_STATUS_TYPE_NOT_SUPPORTED", "connection status not support"),
    EXTRA_DEVICE_INFO_OVERLENGTH("3014", "EXTRA_DEVICE_INFO_OVERLENGTH", "lenght of extraDeviceInfo wrong"),
    RULESET_OVERLENGTH("3015", "RULESET_OVERLENGTH", "lenght of ruleSet error"),
    NEED_APPVERSION("3016", "NEED_APPVERSION", "need appVersion"),
    NEED_TNTINSTID("3017", "NEED_TNTINSTID", "need tenant ID"),
    PAYLOAD_FORMAT_WRONG("3018", "PAYLOAD_FORMAT_WRONG", "format of payload error"),
    TASKNAME_NULL("3019", "TASKNAME_NULL", "taskname can't be null"),
    EXPIREDSECONDS_WRONG("3020", "EXPIREDSECONDS_WRONG", "'expiredseconds' wrong value"),
    TOKEN_OR_USERID_NULL("3021", "TOKEN_OR_USERID_NULL", "'token_or_userid' can't be null"),
    TEMPLATE_NOT_EXIST("3022", "TEMPLATE_NOT_EXIST", "template not exist"),
    TEMPLATEKV_NOT_ENOUGH("3023", "TEMPLATEKV_NOT_ENOUGH", "'tempaltekv' not enough"),
    PAYLOAD_NOT_ENOUGH("3024", "PAYLOAD_NOT_ENOUGH", "'title'or'content' can't be null"),
    NEED_TEMPLATE("3025", "NEED_TEMPLATE", "need template"),
    EXPIREDTIME_TOO_LONG("3026", "EXPIREDTIME_TOO_LONG", "ecpiredtime too long"),
    NEED_TENANTID("3027", "NEED_TENANTID", "need tenantId"),
    INVALID_PARAM("3028", "INVALID_PARAM", "param is invalid"),

    NOT_PUSH_DEVICE("8000", "NOT_PUSH_DEVICE", "not push device"),
    NOT_PUSH_USER("8001", "NOT_PUSH_USER", "not push user"),
    NOT_ONLINE("8002", "NOT_ONLINE", "user not online"),
    SWITCH_OFF("8003", "SWITCH_OFF", "switch off "),
    DEVICE_NOT_ONLINE("8004", "DEVICE_NOT_ONLINE", "device not online"),
    DEVICE_UNBIND("8005", "DEVICE_UNBIND", "device unbind"),
    MSG_EXPIRED("8006", "MSG_EXPIRED", "msg expired"),
    DELIVERY_TOKEN_NOT_MATCH("8007", "DELIVERY_TOKEN_NOT_MATCH", "DeliverToken mismatch"),
    CONNECTTYPE_UNPASS("8008", "CONNECTTYPE_UNPASS", "connection type not accepted"),
    MODEL_UNPASS("8009", "MODEL_UNPASS", "device model not accepted"),
    CHANNEL_UNPASS("8010", "CHANNEL_UNPASS", "dispatch channel not accepted"),

    SYSTEM_ERROR("9000", "SYSTEM_ERROR", "system error");

    private String code;

    private String reason;

    private String memo;

    private PushResultEnum(String code, String reason, String memo) {
        this.code = code;
        this.reason = reason;
        this.memo = memo;
    }

    public static PushResultEnum getResult(String code) {
        PushResultEnum type = null;
        for (PushResultEnum v : values()) {
            if (v.getCode().equals(code)) {
                type = v;
                break;
            }
        }
        return type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
