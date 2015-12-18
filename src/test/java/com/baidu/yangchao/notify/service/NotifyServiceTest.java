/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.yangchao.notify.BaseTest;
import com.baidu.yangchao.notify.common.conf.NotifyEnum;
import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class NotifyServiceTest extends BaseTest {

    @Autowired
    private NotifyService notifyService;

    /**
     * 测试发送邮件
     */
    @Test
    public void testSendEMail() {
        EmailDO emailDO = mockEmailDO();
        notifyService.sendEmail(emailDO);
    }

    /**
     * 测试发送短信
     */
    @Test
    public void testSendSms() {
        SmsDO smsDO = mockSmsDO();
        notifyService.sendSms(smsDO);
    }

    private SmsDO mockSmsDO() {
        SmsDO smsDO = new SmsDO();
        smsDO.setContent("content");
        smsDO.setTo("15921643521");
        return smsDO;
    }

    private EmailDO mockEmailDO() {
        EmailDO request = new EmailDO();
        request.setContent("content");
        request.setFrom("from");
        request.setSendType(NotifyEnum.EMAIL.getValue());
        request.setSubject("subject");
        request.setTo("to");
        return request;
    }
}
