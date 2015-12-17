/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.domain.http;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class BaseRequest {
    // 服务标识token,注册服务使用
    //    private String service_token;
    // 记录轨迹，用于区分记录是否是同一个，类路径+方法名+所在行号
    //    private String traceInfo;
    // 0: email, 1: sms, 2: hi
    private Integer sendType;

    //    public String getService_token() {
    //        return service_token;
    //    }
    //
    //    public void setService_token(String service_token) {
    //        this.service_token = service_token;
    //    }
    //
    //    public String getTraceInfo() {
    //        return traceInfo;
    //    }
    //
    //    public void setTraceInfo(String traceInfo) {
    //        this.traceInfo = traceInfo;
    //    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }
}
