/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.csii.controller.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 加签类
 * @author skywalker
 * @version $Id: SignUtil.java, v 0.1 2018年05月16日 下午9:11 skywalker Exp $
 */
public class SignUtil {

    public static String sign(String content,String priKey,String charset){
        try {
            PrivateKey pKey = getPrivateKeyFromX509("RSA",priKey);
            java.security.Signature signature = java.security.Signature.getInstance("SHA256WithRSA");
            signature.initSign(pKey);
            signature.update( content.getBytes(charset) );

            return  new String(Base64.encodeBase64(signature.sign()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PrivateKey getPrivateKeyFromX509(String algorithm, String ins) {
        try {

            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

            byte[] encodedKey = ins.getBytes("utf-8");
            encodedKey = Base64.decodeBase64(encodedKey);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}