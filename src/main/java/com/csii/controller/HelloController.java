package com.csii.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.gateway.adapterservice.annotation.OperationType;

@Controller
public class HelloController {

    @ResponseBody
    @OperationType(value = "com.csii.gateWay.hello.http.get", name = "hello", desc = "测试工程")
    @RequestMapping(value="/hello",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String hello(){
    	 JSONObject result = new JSONObject();
         result.put("msg", "ok");
         result.put("method", "request");
         result.put("response", "request is success!");
         return result.toJSONString();
    }
}
