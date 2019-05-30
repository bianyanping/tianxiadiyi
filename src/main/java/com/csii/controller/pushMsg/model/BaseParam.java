package com.csii.controller.pushMsg.model;

import java.util.Map;

/** 基础参数类
 * @author wangguang.wg
 */
public class BaseParam {

    /**
     * 应用标识
     */
    private String appId;

    /**
     * 环境标识
     */
    private String workspaceId;

    /**
     * 消息类型：通知栏消息还是透明消息,默认通知栏消息，参照NotifyType枚举，默认通知栏消息
     */
    private String notifyType;

    /**
     * 跳转方式:0,intent;1,web;默认web
     */
    private int action;

    /**
     * 1:静默消息 or 0:展示消息,默认展示消息
     */
    private int silent;

    /**
     * 自定义扩展参数，该参数可以携带到客户端
     */
    private Map<String,String> extended_params;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getSilent() {
        return silent;
    }

    public void setSilent(int silent) {
        this.silent = silent;
    }

    public Map<String, String> getExtended_params() {
        return extended_params;
    }

    public void setExtended_params(Map<String, String> extended_params) {
        this.extended_params = extended_params;
    }
}
