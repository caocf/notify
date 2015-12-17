/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baidu.yangchao.notify.common.factory.EmailFactory;
import com.baidu.yangchao.notify.common.factory.QueueFactory;
import com.baidu.yangchao.notify.common.factory.SmsFactory;
import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;
import com.baidu.yangchao.notify.service.QueueService;

/**
 * 通过使用安全的无限队列使得请求可以快速返回
 * 启动子线程，将对象放入BlockingQueue，通过阻塞队列，限制处理流量大小
 * <p/>
 * Created by yangchao
 * on 15/12/17.
 */
@Service
public class QueueServiceImpl implements QueueService {

    private static final EmailFactory emailFactory = EmailFactory.getInstance();
    private static final SmsFactory smsFactory = SmsFactory.getInstance();
    private static final QueueFactory queueFactory = QueueFactory.getInstance();
    private static Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);

    @PostConstruct
    public void init() {
        workEmail();
        workSms();
    }

    private void workSms() {
        new Thread(() -> {
            while (true) {
                SmsDO smsDO = queueFactory.getSms();
                try {
                    // 如果超过队列大小，则则会阻塞，用于限流
                    smsFactory.produce(smsDO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        logger.info("email into blocking queue task start ...");
    }

    private void workEmail() {
        new Thread(() -> {
            while (true) {
                EmailDO emailDO = queueFactory.getEmail();
                try {
                    // 如果超过队列大小，则则会阻塞，用于限流
                    emailFactory.produce(emailDO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        logger.info("email into blocking queue task start ...");
    }

    @Override
    public void importEmail(EmailDO emailOD) throws InterruptedException {
        if (null != emailOD) {
            // 插入队列/缓存
            queueFactory.putEmail(emailOD);
            logger.info("put a email into queue!");
        }
    }

    @Override
    public void importSms(SmsDO smsDO) throws InterruptedException {
        if (null != smsDO) {
            // 插入队列/缓存
            queueFactory.putSms(smsDO);
            logger.info("put a sms into queue");
        }
    }
}
