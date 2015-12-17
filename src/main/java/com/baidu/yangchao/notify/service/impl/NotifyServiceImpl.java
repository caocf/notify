/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baidu.yangchao.notify.common.factory.EmailFactory;
import com.baidu.yangchao.notify.common.factory.SmsFactory;
import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;
import com.baidu.yangchao.notify.service.NotifyService;

/**
 * Created by yangchao
 * on 15/12/17.
 */
@Service
public class NotifyServiceImpl implements NotifyService {
    private static final EmailFactory emailFactory = EmailFactory.getInstance();
    private static final SmsFactory smsFactory = SmsFactory.getInstance();
    private static Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);

    @PostConstruct
    void init() {
        workEmail();
        workSms();
        // todo hi
    }

    private void workEmail() {
        new Thread(() -> {
            while (true) {
                EmailDO emailDO = null;
                try {
                    emailDO = emailFactory.cosume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 异步执行
                sendEmail(emailDO);
            }
        }).start();

        logger.info("email notify task start ...");
    }

    private void workSms() {
        new Thread(() -> {
            while (true) {
                SmsDO smsDO = null;
                try {
                    smsDO = smsFactory.cosume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendSms(smsDO);
            }
        }).start();

        logger.info("sms notiry task start ...");
    }

    /**
     * 发送邮件（使用spring 异步机制执行）
     *
     * @param emailDO
     */
    @Override
    @Async
    public void sendEmail(EmailDO emailDO) {
        if (null != emailDO) {
            String to = emailDO.getTo();
            String from = emailDO.getFrom();
            String subject = emailDO.getSubject();
            String content = emailDO.getContent();

            // todo 发送邮件
            logger.info("send email success!");

        }

    }

    /**
     * 发送短信（使用spring 异步机制执行）
     *
     * @param smsDO
     */
    @Override
    @Async
    public void sendSms(SmsDO smsDO) {
        if (null != smsDO) {
            String mobiles = smsDO.getTo();
            String content = smsDO.getContent();
            // todo 发送短信
            //            SmsUtils.sendSms(mobiles, content);
            logger.info("send sms success!");
        }
    }
}
