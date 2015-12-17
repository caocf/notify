/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.domain.http;

/**
 * 消息请求对象
 * Created by yangchao
 * on 15/12/14.
 */
public class NotifyRequest extends BaseRequest {

    private String from;            // 消息来源
    private String to;              // 消息终点
    private String subject;         // 消息主题
    private String content;         // 消息内容

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
