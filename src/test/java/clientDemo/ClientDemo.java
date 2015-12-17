/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package clientDemo;

import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * 发送消息请求客户端类
 * <p/>
 * springboot 启动 消息中心后，执行该类main方法
 * <p/>
 * Created by yangchao
 * on 15/12/17.
 */
public class ClientDemo {

    private static Logger log = LoggerFactory.getLogger(ClientDemo.class);

    private final static String URL = "http://127.0.0.1:8080/notify/do";

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) throws Exception {
        sendEmail();
        sendSms();
    }

    public static void httpPostWithJSON(String url, String json) throws Exception {
        String encoderJson = json;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

        StringEntity se = new StringEntity(encoderJson);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        httpClient.execute(httpPost);
    }

    private static void sendSms() throws Exception {
        Map<String, String> request = Maps.newHashMap();
        request.put("sendType", "1");
        request.put("to", "15921643521");
        request.put("content", "sms content");
        String json = JSON.toJSONString(request);
        httpPostWithJSON(URL, json);
    }

    private static void sendEmail() throws Exception {
        Map<String, String> request = Maps.newHashMap();
        request.put("sendType", "0");
        request.put("from", "yangchao@baidu.com");
        request.put("to", "test@baidu.com");
        request.put("subject", "email titile");
        request.put("content", "content");
        String json = JSON.toJSONString(request);
        httpPostWithJSON(URL, json);
    }

}
