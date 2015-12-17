/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.domain;

import com.baidu.yangchao.notify.domain.http.NotifyRequest;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class SmsDO extends Notify {
    private String to;              // 目的手机
    private String content;         // 短信内容

    public static SmsDO build(NotifyRequest request) throws Exception {
        if (null == request || null == request.getTo() || null == request.getContent()) {
            throw new Exception("sms request build fail");
        }
        SmsDO smsDO = new SmsDO();
        String to = request.getTo();
        if (null != to) {
            smsDO.setTo(to);
        }
        String content = request.getContent();
        if (null != content) {
            smsDO.setContent(content);
        }
        return smsDO;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
