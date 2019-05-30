/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.csii.controller.pushMsg.enums;

/**
 * 
 *  推送状态码
 * @author yangqun.yq
 * @version $Id: StatusType.java, v 0.1 2014-10-28 下午6:10:59 yangqun.yq Exp $
 */
public enum StatusType {
    TO_CHECK(-1,"WaitingForVerify"),//"等待校验"

    TO_PUSH(0, "DeviceNotOnlineOrNoResponse"),//"等待设备上线或等待发送确认"

    TO_BIND(1, "NoBindInfo"), //"等待绑定关系"

    ACKED(2, "Acked"),//"客户端已确认"),

    CLICKED(3, "Clicked"),//"客户端已点击"),


    EXCEPTION(1000, "Exception"),//"异常"),

    FAIL_START(2000, "ERROR START INDEX"),

    FAIL_APNS_PayloadEmpty(2001,"PayloadEmpty"),		                //The message payload was empty.
    FAIL_APNS_PayloadTooLarge(2002,"PayloadTooLarge"),			        //The message payload was too large. The maximum payload size is <code>4096</code> bytes.
    FAIL_APNS_BadTopic(2003,"BadTopic"),			                //The <code>apns-topic</code> was invalid.
    FAIL_APNS_TopicDisallowed(2004,"TopicDisallowed"),			        //Pushing to this topic is not allowed.
    FAIL_APNS_BadMessageId(2005,"BadMessageId"),			        //The <code>apns-id</code> value is bad.
    FAIL_APNS_BadExpirationDate(2006,"BadExpirationDate"),		        //The <code>apns-expiration</code> value is bad.
    FAIL_APNS_BadPriority(2007,"BadPriority"),			                //The <code>apns-priority</code> value is bad.
    FAIL_APNS_MissingDeviceToken(2008,"MissingDeviceToken"),		        //The device token is not specified in the request <code>:path</code>. Verify that the <code>:path</code> header contains the device token.
    FAIL_APNS_BadDeviceToken(2009,"BadDeviceToken"),			        //The specified device token was bad. Verify that the request contains a valid token and that the token matches the environment.
    FAIL_APNS_DeviceTokenNotForTopic(2010,"DeviceTokenNotForTopic"),		//The device token does not match the specified topic.
    FAIL_APNS_Unregistered(2011,"Unregistered"),			        //The device token is inactive for the specified topic.</p><p>Expected HTTP/2 status code is <code>410</code>; see <span class="content_text"><a href="#//apple_ref/doc/uid/TP40008194-CH101-SW3" data-renderer-version="1" onclick="s_objectID=&quot;https://developer.apple.com/library/ios/documentation/NetworkingInternet/Conceptual/RemoteNotific_9&quot;;return this.s_oc?this.s_oc(e):true">Table 6-4</a></span>.
    FAIL_APNS_DuplicateHeaders(2012,"DuplicateHeaders"),		        //One or more headers were repeated.
    FAIL_APNS_BadCertificateEnvironment(2013,"BadCertificateEnvironment"),	//The client certificate was for the wrong environment.
    FAIL_APNS_BadCertificate(2014,"BadCertificate"),			        //The certificate was bad.
    FAIL_APNS_Forbidden(2015,"Forbidden"),			                //The specified action is not allowed.
    FAIL_APNS_BadPath(2016,"BadPath"),				                //The request contained a bad <code>:path</code> value.
    FAIL_APNS_MethodNotAllowed(2017,"MethodNotAllowed"),		        //The specified <code>:method</code> was not <code>POST</code>.
    FAIL_APNS_TooManyRequests(2018,"TooManyRequests"),			        //Too many requests were made consecutively to the same device token.
    FAIL_APNS_IdleTimeout(2019,"IdleTimeout"),			                //Idle time out.
    FAIL_APNS_Shutdown(2020,"Shutdown"),			                //The server is shutting down.
    FAIL_APNS_InternalServerError(2021,"InternalServerError"),		        //An internal server error occurred.
    FAIL_APNS_ServiceUnavailable(2022,"ServiceUnavailable"),		        //The service is unavailable.
    FAIL_APNS_MissingTopic(2023,"MissingTopic"),			        //The <code>apns-topic</code> header of the request was not specified and was required. The apns-topic header is mandatory when the client is connected using a certificate that supports multiple topics. </p></td></tr></tbody>
    FAIL_APNS_CONN_CLOSED(2024,"ConnClosed"),			        //The connection closed unexpectedly
    FAIL_APNS_CONN_UNAVAILABLE(2025,"ConnUnavailable"),			        //The connection is unavailable
    FAIL_APNS_APP_VERSION_OUTOFBOUND(2026,"AppVersionOutOfBound"), //App_version out of bound
    FAIL_APNS_APP_MINVERSION_FORMATERROR(2027,"AppMinVersionFormatError"), //App min_version format eorror
    FAIL_APNS_APP_MAXVERSION_FORMATERROR(2028,"AppMaxVersionFormatError"), //App max_version format eorror
    FAIL_APNS_APP_VERSION_FORMATERROR(2029,"AppVersionFormatError"), //App_version format eorror
    FAIL_APNS_APP_VERSION_ISNULL(2030,"AppVersionIsNull"),//App_version is null
    FAIL_APNS_APP_MAXVERSIONLESSTHANMINVERSION(2031,"MaxVersionLessThanMinVersion"),//Max_version less than min version

    FAIL_END(2999, "ERROR END INDEX"),


    NONE(9999999,"NONE");

    private int    value;

    private String description;

    public static StatusType valueof(int type) {
        for (StatusType item : values()) {
            if (type == item.value) {
                return item;
            }
        }
        return StatusType.NONE;
    }

    public static StatusType describeas(String des) {
        for (StatusType item : values()) {
            if (des.equals(item.description)) {
                return item;
            }
        }
        return StatusType.NONE;
    }

    public static boolean isFailureStatusType(int statusValue){
        StatusType statusType = StatusType.valueof(statusValue);
        return StatusType.isFailureStatusType(statusType);
    }

    public static boolean isFailureStatusType(StatusType statusType){
        if (null != statusType
                && statusType.getValue() > FAIL_START.getValue()
                && statusType.getValue() < FAIL_END.getValue()){
            return true;
        }
        return false;
    }

    private StatusType(int value, String description) {
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
