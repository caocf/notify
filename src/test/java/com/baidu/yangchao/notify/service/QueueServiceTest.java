/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.yangchao.notify.BaseTest;
import com.baidu.yangchao.notify.common.conf.NotifyEnum;
import com.baidu.yangchao.notify.common.factory.QueueFactory;
import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;
import com.google.common.collect.Lists;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class QueueServiceTest extends BaseTest {

    private static Logger log = Logger.getLogger(QueueServiceTest.class);

    @Autowired
    private QueueService queueService;

    private final static QueueFactory queueFactory = QueueFactory.getInstance();

    @Before
    public void before() {
    }

    @Test
    public void testImportNotifyEmail() throws Exception {
        EmailDO emailDO = mockEmail();
        queueService.importEmail(emailDO);
        List<EmailDO> emailDOs = Lists.newArrayList(queueFactory.getEmail());
        assertEquals(1, emailDOs.size());
    }

    @Test
    public void testImportNotifySms() throws Exception {
        SmsDO smsDO = mockSmsDO();
        queueService.importSms(smsDO);
        List<SmsDO> smsDOs = Lists.newArrayList(queueFactory.getSms());
        assertEquals(1, smsDOs.size());
    }

    @After
    public void after() {

    }

    private SmsDO mockSmsDO() {
        SmsDO smsDO = new SmsDO();
        smsDO.setContent("content");
        smsDO.setTo("15921643521");
        return smsDO;
    }

    private EmailDO mockEmail() {
        EmailDO request = new EmailDO();
        request.setContent("content");
        request.setFrom("from");
        request.setSendType(NotifyEnum.EMAIL.getValue());
        request.setSubject("subject");
        request.setTo("to");
        return request;
    }
}
