package com.csii.controller.util;

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */

import java.net.ProxySelector;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/** Http工具类
 * @author wangguang.wg
 * @date 2017-10-26 15:53:26
 */
public class HttpClientUtil {

    /**
     * 最大连接数
     */
    private final static int                          MAX_TOTAL_CONNECTIONS      = 800;

    private static final int                          IDEL_TIME                  = 30;

    /**
     * 每个路由最大连接数
     */
    private final static int                          MAX_ROUTE_CONNECTIONS      = 400;

    private final static int                          SOCKET_TIMEOUT             = 120 * 1000;

    private final static int                          CONNECTION_REQUEST_TIMEOUT = 120 * 1000;

    private final static int                          CONNECTION_TIMEOUT         = 120 * 1000;

    private final static int                          MAX_HEADER_COUNT           = 200;

    private final static int                          MAX_LINE_LENGTH            = 2000;

    private static PoolingHttpClientConnectionManager connManager;

    private static CloseableHttpClient                httpClient;

    static {
        httpClient = createHttpClient(MAX_TOTAL_CONNECTIONS, MAX_ROUTE_CONNECTIONS);
    }

    /**
     *
     *
     * @param maxConnections
     * @param maxRouteConnctions
     * @return
     */
    public static CloseableHttpClient createHttpClient(int maxConnections, int maxRouteConnctions) {
        //获取JVM配置的代理参数
        SystemDefaultRoutePlanner routePlanner = new SystemDefaultRoutePlanner(ProxySelector.getDefault());

        connManager = new PoolingHttpClientConnectionManager();
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true)
                .setSoTimeout(SOCKET_TIMEOUT).build();
        connManager.setDefaultSocketConfig(socketConfig);
        MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(MAX_HEADER_COUNT).setMaxLineLength(MAX_LINE_LENGTH).build();
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints).build();
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
        connManager.closeIdleConnections(IDEL_TIME, TimeUnit.MILLISECONDS);
        connManager.closeExpiredConnections();

        return HttpClients.custom().setRoutePlanner(routePlanner).setConnectionManager(connManager).build();
    }

    /**
     * Get请求
     *
     * @param url
     * @return
     */
    public static HttpInvokeResult invokeGet(String url) {
        HttpGet get = new HttpGet(url);
        get.setConfig(getRequestConfig());
        CloseableHttpResponse response = null;
        return doInvokeGet(get, response);
    }

    /**
     *
     *
     * @param url
     * @param headers
     * @return
     */
    public static HttpInvokeResult invokeGetWithHeader(String url, Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        get.setConfig(getRequestConfig());
        CloseableHttpResponse response = null;
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey() == null || entry.getKey().equals("")||entry.getValue() == null || entry.getValue().equals("")) {
                    continue;
                }
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return doInvokeGet(get, response);
    }

    /**
     * post请求，默认超时为5s
     *
     * @param reqURL
     * @param params
     * @return
     */
    public static HttpInvokeResult invokePost(String reqURL, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(reqURL);
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            formParams.add(pair);
        }

        UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        httpPost.setEntity(urlEntity);
        httpPost.setConfig(getRequestConfig());

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            boolean success = isSuccess(statusCode);
            if (entity != null) {
                return new HttpInvokeResult(success, EntityUtils.toString(entity));
            } else {
                return new HttpInvokeResult(success, "");
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            handleFinally(httpPost, response);
        }
    }


    /**
     * post方法以json的格式进行http传输,默认超时为5s
     *
     * @param url     远程url
     * @param content 传输内容
     * @return
     * @throws RuntimeException
     */
    public static HttpInvokeResult invokePost(String url, String content) throws RuntimeException {
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = null;

        try {
            if (content != null && content.length() > 0) {
                httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
            }

            httpPost.setConfig(getRequestConfig());
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();

            boolean success = isSuccess(statusCode);
            if (entity != null) {
                return new HttpInvokeResult(success, EntityUtils.toString(entity), statusCode);
            } else {
                return new HttpInvokeResult(success, "");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            handleFinally(httpPost, response);
        }
    }



    /**
     * put方式提交JSON数据
     *
     * @param url            ：服务器地址
     * @param content:json内容
     * @return
     * @throws Exception
     */
    public static HttpInvokeResult invokePutWithBody(String url, String content) throws RuntimeException {
        HttpPut httpPut = new HttpPut(url);

        CloseableHttpResponse response = null;
        try {
            if (content != null && content.length() > 0) {
                httpPut.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
            }
            httpPut.setConfig(getRequestConfig());
            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            boolean success = isSuccess(statusCode);
            if (entity != null) {
                return new HttpInvokeResult(success, EntityUtils.toString(entity));
            } else {
                return new HttpInvokeResult(success, "");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpPut.releaseConnection();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static RequestConfig getRequestConfig() {
        return RequestConfigHolder.REQUEST_CONFIG;
    }

    private static void handleFinally(HttpRequestBase get, CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
            get.releaseConnection();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static HttpInvokeResult doInvokeGet(HttpGet get, CloseableHttpResponse response) {
        try {
            response = httpClient.execute(get);
            HttpEntity entity;
            int statusCode = response.getStatusLine().getStatusCode();
            boolean success = isSuccess(statusCode);
            entity = response.getEntity();
            if (entity != null) {
                return new HttpInvokeResult(success, EntityUtils.toString(entity));
            } else {
                return new HttpInvokeResult(success, "");
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            handleFinally(get, response);
        }
    }

    /**
     * 判断结果是否成功, code是2xx代表成功(阿里云)
     *
     * @param statusCode
     * @return
     */
    private static boolean isSuccess(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    /**
     * RequestConfigHolder
     */
    private static class RequestConfigHolder {
        private static final RequestConfig REQUEST_CONFIG = RequestConfig
                .custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(
                        CONNECTION_REQUEST_TIMEOUT)
                .build();
    }


    public static class HttpInvokeResult {
        private boolean success;
        private String  result;
        private int     code ;

        public HttpInvokeResult(boolean success, String result, int code) {
            this.success = success;
            this.result = result;
            this.code = code;
        }

        public HttpInvokeResult(boolean success, String result) {
            this.success = success;
            this.result = result;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getResult() {
            return this.result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        @Override
        public String toString(){
            return String.format("code:%d, success:%s, result:%s", code,success, result);
        }
    }
}