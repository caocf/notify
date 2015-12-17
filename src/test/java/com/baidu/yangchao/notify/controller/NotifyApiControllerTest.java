/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.baidu.yangchao.notify.BaseTest;
import com.baidu.yangchao.notify.common.conf.NotifyEnum;
import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;
import com.baidu.yangchao.notify.domain.http.BaseResponse;
import com.baidu.yangchao.notify.domain.http.NotifyRequest;
import com.baidu.yangchao.notify.service.QueueService;

import junit.framework.Assert;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class NotifyApiControllerTest extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger(NotifyApiControllerTest.class);
    private static NotifyRequest emailRequest;
    private static NotifyRequest smsRequest;
    @Autowired
    @InjectMocks
    NotifyApiController notifyApiController;
    @Mock
    QueueService queueService;

    @Before
    public void init() throws InterruptedException {
        emailRequest = mockEmail();
        smsRequest = mockSms();
        MockitoAnnotations.initMocks(this);
        Mockito.doNothing().when(queueService).importEmail(Mockito.any(EmailDO.class));
        Mockito.doNothing().when(queueService).importSms(Mockito.any(SmsDO.class));

    }

    @Test
    public void testWork() {
        BaseResponse<?> r1 = notifyApiController.notify(emailRequest);
        logger.info("send email response is " + JSON.toJSONString(r1));
        Assert.assertEquals(BaseResponse.SUCCESS, r1.getErrorCode());

        BaseResponse<?> r2 = notifyApiController.notify(smsRequest);
        logger.info("send sms response is " + JSON.toJSONString(r2));
        Assert.assertEquals(BaseResponse.SUCCESS, r2.getErrorCode());

    }

    @Test
    public void testErrorRequest() {
        NotifyRequest errorRequest = new NotifyRequest();
        errorRequest.setSendType(null);
        BaseResponse<?> r = notifyApiController.notify(errorRequest);
        logger.info("send email response is " + JSON.toJSONString(r));
        Assert.assertEquals(BaseResponse.FAIL, r.getErrorCode());

        // 错误的发送类型
        errorRequest.setSendType(1000);
        r = notifyApiController.notify(errorRequest);
        logger.info("send email response is " + JSON.toJSONString(r));
        Assert.assertEquals(BaseResponse.FAIL, r.getErrorCode());

        // 短信发送时，缺少目的手机号
        errorRequest.setSendType(1);
        errorRequest.setTo(null);
        r = notifyApiController.notify(errorRequest);
        logger.info("send email response is " + JSON.toJSONString(r));
        Assert.assertEquals(BaseResponse.FAIL, r.getErrorCode());

        // 邮件发送时，缺少目的邮箱
        errorRequest.setSendType(0);
        errorRequest.setTo(null);
        r = notifyApiController.notify(errorRequest);
        logger.info("send email response is " + JSON.toJSONString(r));
        Assert.assertEquals(BaseResponse.FAIL, r.getErrorCode());

    }

    private NotifyRequest mockSms() {
        NotifyRequest notifyRequest = new NotifyRequest();
        notifyRequest.setSendType(NotifyEnum.SMS.getValue());
        notifyRequest.setTo("15921643521");
        notifyRequest.setContent("sms content");
        return notifyRequest;
    }

    private NotifyRequest mockEmail() {
        NotifyRequest notifyRequest = new NotifyRequest();
        notifyRequest.setSendType(NotifyEnum.EMAIL.getValue());
        notifyRequest.setFrom("yangchao@baidu.com");
        notifyRequest.setTo("test@baidu.com");
        notifyRequest.setSubject("subject");
        notifyRequest.setContent("email content");
        return notifyRequest;
    }

}
