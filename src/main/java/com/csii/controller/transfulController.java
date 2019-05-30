package com.csii.controller;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.gateway.adapterservice.annotation.OperationType;

@Controller
public class transfulController {

    @ResponseBody
    @OperationType(value = "com.csii.gateWay.transful", name = "transful", desc = "测试工程")
    @RequestMapping(value="/transful",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String transful(){
    	 JSONObject result = new JSONObject();
         result.put("LimitWarning", "ok");
         result.put("_TransDate", new Date());
         result.put("plainAmount", 1000.00);
         result.put("PayeeAcName", "令狐冲");
         result.put("plainPayeeAcNo", "622013234243242342");
         result.put("PayerAcName", "任盈盈");
         result.put("PayerAcNo", "63434324234243432");
         result.put("Remark", "零花钱");
         result.put("Fee", 5.00);
         result.put("_JnlNo", "1231313213123");
         return result.toJSONString();
    }
}
