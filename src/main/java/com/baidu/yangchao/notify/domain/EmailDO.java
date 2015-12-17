/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.domain;

import com.baidu.yangchao.notify.domain.http.NotifyRequest;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class EmailDO extends Notify {
    private String from;            // 来源邮箱
    private String to;              // 目的邮箱
    private String subject;         // 邮件标题
    private String content;         // 邮件内容

    public static EmailDO build(NotifyRequest request) throws Exception {
        if (null == request || null == request.getFrom() || null == request.getTo() || null == request.getSubject()
                || null == request.getContent()) {
            throw new Exception("email request build fail");
        }
        EmailDO emailDO = new EmailDO();
        String from = request.getFrom();
        if (null != from) {
            emailDO.setFrom(from);
        }
        String to = request.getTo();
        if (null != to) {
            emailDO.setTo(to);
        }
        String subject = request.getSubject();
        if (null != subject) {
            emailDO.setSubject(subject);
        }
        String content = request.getContent();
        if (null != content) {
            emailDO.setContent(content);
        }
        return emailDO;
    }

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
