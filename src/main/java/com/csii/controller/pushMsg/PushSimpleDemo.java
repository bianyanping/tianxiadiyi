/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.csii.controller.pushMsg;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.csii.controller.pushMsg.enums.DeliveryType;
import com.csii.controller.pushMsg.model.PushSimpleReq;
import com.csii.controller.util.HttpClientUtil;
import com.csii.controller.util.SignUtil;


/**
 * 简单推送样例
 * @author wangguang.wg
 * @version $Id: PushSimpleDemo.java, v 0.1 2018年05月16日 下午8:17 wangguang.wg Exp $
 */
public class PushSimpleDemo {

    /**
     * 推送接口服务地址
     */
    private static String mpsAPIAddress = "https://cn-hangzhou-mps-api.cloud.alipay.com";

    /**
     * 应用标识
     */
    private static final String appId       = "CF280C7171148";

    /**
     * 环境标识
     */
    private static final String workSpaceId   = "mpassDev";

    /**
     * 字符集设置
     */
    private static final String charSet     = "UTF-8";

    /**
     * RSA私钥，要求2048位的密钥对，接口调用前请现在mpaas控制台登记该RSA私钥对应的公钥
     */
    private static final String rsaKey ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC8JpmGYX7CfewIYdnzm8015cRBnUvaSkgVXEkBsJo84O4FrrhKBqtxYhDCaCss2qOeWzR3FuKiPtjGXSDHwXJE/Prg88Gm6mJ2c2EGc9pMiIPIdGhCvCxwV9Mkou4CwYycF8sw8/RegoI+BJH25ccrrJK8ELYMSawtuwsqaeHv4hmKELfCpztg80jD1PtT0shbAbtvMYhlgZhh0pSB1uM9tcocNSy2sC518tTQoy+GVnnQa1xrvgAQ8M1uzDVJRFBzTyxsiAFGnmniTSvZ0EFs1isd7YrSAwimb2EpYGSbbe3biJp5jPUdwxVDwRIQ4ZJ2xaHxkGIC1ASjRJstsRE9AgMBAAECggEBALRFJ4OhlhozHBtL5c+Kz6t8YfWcCDk2Qqp0qV59HsqFmIjJRrlmeo1AMF6UAAoR0gK/0BzqSGq7XGdygx3hMuL2oEk4Q6rxlzV2htfv2JDLm7hMEDkgolNhUmQf13u8hkRoZ76XfuOul6tkJZYxwgsOZeja+hH75IRMWf5hRKDYkdD1iYdwfETm6YOuRwhPWOJuJ8vNNnulMKQBYw/JLmSdsY0KSJB5LjFJN9U5FwKnggws1JPIpcPxYydjddBKe8RDkH9kf6u4vuUyuMPwX9DW7zjDuo9//rewJApwgSNYy5rtCblpAZaLYl8Ah6mNVmpCVLPg6jnQP7jBjEgZ7OECgYEA9lotzWGRkPMLHBylLOouovgvy+jBIhuuHsuDs3ObcQu3tZYFsCfcmxd/K5NSNb2zELQecdYX4li65xRh3BCU6b1DfeeZscLZUwL6njYO9Jv+bxWfU1s+0s6TDj3ZCr5vuGObGQg4oVBsnz/LXrf1inSSeEYc1xxK7Tk645rla08CgYEAw4Tq/nuXyXUaHYqdxm12vX9+PCGaj+9eNE6DS9WYHU5JREMlDy++rKsqiEV/wwoamfyBOo45TddtWZ0WX+zJkzQ0oKVD5dbXFR6XFkWfKzd9OdUFkW/wehvmxz/aq1qe2FXwjXOZ8xDlo8xOQebYRC+hYAwZ49ULH6lwAwGyJ7MCgYAfWojby+Av947hbA/lsIRw/29t31i9U5KMntBLRZpx/9DIzqg4rEoafxoTXLOo3VTt+k6ppFrz1uEqyeBOltmtCgaN/q0D/82TEBz/h9fNcBEo6DKX9FQBloMG2/5ErMohSKQTwoUamtbISeFDU9xvW7jMj/fZtOMgYBlLoIo0ZwKBgCIJdKNivrTahvZXmEnf6ojotgAf1XlzMVInzOGZyIZph4WzrrLrG+WRUrEwcCJzEth1xcV81ur/MtEj8K44WYsfzn2c56oOcZkCn0jITAJJAarcs8By3UlUrCNJsXadKwP8SglF86oF6gRO39zHJCUSqaQ/1w37m5kidRaA6BPTAoGBAJtMtJTU29S7ZxLUlYbWYLx3rZR2Mk99c/ackgNRxN3QEXBlugE52070VKtgwfcFLKnYO2DLKbKJWBumyrD4y3t5g2TtKcZrBOQwfnjAZOlHHLyTsTj9xzCdRxNx3DMbHvOHUDcoNgifAVlUW65NTHAq46r/5PgGKWnpXTstZEQS";

    public static void main(String args[]){
        PushSimpleReq pushSimpleReq = new PushSimpleReq();
        pushSimpleReq.setTaskName("测试任务");
        pushSimpleReq.setContent("简单推送样例-内容");
        pushSimpleReq.setTitle("简单推送样例-标题");
        pushSimpleReq.setUri("http://127.0.0.1");
        pushSimpleReq.setDeliveryType(DeliveryType.DEVICE_IOS.getValue());
        pushSimpleReq.setExpiredSeconds(600);

        Map<String,String> extendedParam = new HashMap<String, String>();
        extendedParam.put("test","自定义扩展参数");
        pushSimpleReq.setExtended_params(extendedParam);

        Map<String,String> target = new HashMap<String, String>();
        String msgKey = String.valueOf(System.currentTimeMillis());
        target.put("15873041378",msgKey);
        pushSimpleReq.setTarget_msgkey(target);

        String reqContent = JSON.toJSONString(pushSimpleReq);

        String sign = SignUtil.sign(reqContent,rsaKey,charSet);

        //sign字段放在path里面，所以需要预先做敏感字符替换
        sign = sign.replace('/', '_').replace('+', '-');
        String url = String.format("%s/push/pushsimple/%s/%s/%s", mpsAPIAddress, appId,workSpaceId, sign);

        //此处的成功是指推送任务接受成功，由于消息推送是异步过程，所以具体有没有下发成功需要根据客户端实际情况进行判断
        //接口调用结果码参考PushResultEnum枚举类
        HttpClientUtil.HttpInvokeResult httpInvokeResult = HttpClientUtil.invokePost(url, reqContent);
        System.out.println(url);
        System.out.println(reqContent);
        System.out.println(httpInvokeResult);
    }
}