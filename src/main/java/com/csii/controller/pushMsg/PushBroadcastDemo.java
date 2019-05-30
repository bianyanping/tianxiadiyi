/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.csii.controller.pushMsg;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.csii.controller.pushMsg.enums.DeliveryType;
import com.csii.controller.pushMsg.model.PushBroadcastReq;
import com.csii.controller.util.HttpClientUtil;
import com.csii.controller.util.SignUtil;


/**
 * 模版推送样例
 * @author wangguang.wg
 * @version $Id: PushTemplateDemo.java, v 0.1 2018年05月16日 下午8:17 wangguang.wg Exp $
 */
public class PushBroadcastDemo {

    /**
     * 推送接口服务地址
     */
    private static String mpsAPIAddress = "https://cn-hangzhou-mps-api.cloud.alipay.com";

    /**
     * 应用标识
     */
    private static final String appId       = "请填写实际的appId";

    /**
     * 环境标识
     */
    private static final String workSpaceId   = "请填写实际的workspaceId";

    /**
     * 字符集设置
     */
    private static final String charSet     = "UTF-8";

    /**
     * RSA私钥，要求2048位的密钥对，接口调用前请现在mpaas控制台登记该RSA私钥对应的公钥
     */
    private static final String rsaKey ="请填写实际的私钥";


    public static void main(String args[]){
        PushBroadcastReq pushBroadcastReq = new PushBroadcastReq();
        pushBroadcastReq.setTaskName("测试任务");
        //群发只有两种模式，针对安卓设备或者针对IOS设备
        pushBroadcastReq.setDeliveryType(DeliveryType.DEVICE_ANDROID.getValue());
        pushBroadcastReq.setExpiredSeconds(600);

        //请根据实际的模版名称进行设置
        pushBroadcastReq.setTemplateName("template");

        //模版中配置的变量值，请根据实际模版中的变量进行设置
        Map<String,String> templateKV = new HashMap<String, String>();
        templateKV.put("title", "title-");
        templateKV.put("content", "content-");
        pushBroadcastReq.setTemplatekv(templateKV);

        Map<String,String> extendedParam = new HashMap<String, String>();
        extendedParam.put("test","自定义扩展参数");
        pushBroadcastReq.setExtended_params(extendedParam);

        pushBroadcastReq.setMsgkey(String.valueOf(System.currentTimeMillis()));

        String reqContent = JSON.toJSONString(pushBroadcastReq);

        String sign = SignUtil.sign(reqContent,rsaKey,charSet);

        //sign字段放在path里面，所以需要预先做敏感字符替换
        sign = sign.replace('/', '_').replace('+', '-');
        String url = String.format("%s/push/pushbroadcast/%s/%s/%s", mpsAPIAddress, appId,workSpaceId, sign);

        //此处的成功是指推送任务接受成功，由于消息推送是异步过程，所以具体有没有下发成功需要根据客户端实际情况进行判断
        //接口调用结果码参考PushResultEnum枚举类
        HttpClientUtil.HttpInvokeResult httpInvokeResult = HttpClientUtil.invokePost(url, reqContent);
        System.out.println(url);
        System.out.println(reqContent);
        System.out.println(httpInvokeResult);
    }
}