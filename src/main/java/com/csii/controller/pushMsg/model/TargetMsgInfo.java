package com.csii.controller.pushMsg.model;

import java.util.Map;

/**
 *  针对单个对象的消息配置
 * @author wangguang.wg
 * @date 15/6/1
 */
public class TargetMsgInfo {

    /**
     * 消息ID，请保证业务唯一性，便于排查问题
     */
    private String             msgkey;

    /**
     * 模版中设置变量对应的KV值
     */
    private Map<String,String> templatekv;

    public String getMsgkey() {
        return msgkey;
    }

    public void setMsgkey(String msgkey) {
        this.msgkey = msgkey;
    }

    public Map<String, String> getTemplatekv() {
        return templatekv;
    }

    public void setTemplatekv(Map<String, String> templatekv) {
        this.templatekv = templatekv;
    }
}
