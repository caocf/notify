/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.domain.http;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class BaseResponse<Data> {
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final String OK = "OK";

    private int errorCode;
    private String errorMessage;
    private Data data;

    public BaseResponse(int errorCode, String errorMessage, Data data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public BaseResponse() {

    }

    public static <T> BaseResponse<T> succKBResponse(T data) {
        return new BaseResponse<T>(SUCCESS, OK, data);
    }

    public static BaseResponse<String> failKBResponse(String errorMessage) {
        return failKBResponse(FAIL, errorMessage);
    }

    public static BaseResponse<String> failKBResponse(int errorCode, String errorMessage) {
        return new BaseResponse<String>(errorCode, errorMessage, null);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}