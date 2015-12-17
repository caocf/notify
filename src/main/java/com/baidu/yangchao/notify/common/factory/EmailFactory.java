/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.common.factory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.baidu.yangchao.notify.common.conf.GlobalConf;
import com.baidu.yangchao.notify.domain.EmailDO;

/**
 * Created by yangchao
 * on 15/12/15.
 */
public class EmailFactory {

    private static final EmailFactory emailFactory = new EmailFactory();
    private static BlockingQueue<EmailDO> emailBQ = new ArrayBlockingQueue<>(GlobalConf.EMAIL_SEND_MAX);

    private EmailFactory() {
    }

    public static EmailFactory getInstance() {
        return emailFactory;
    }

    public void produce(EmailDO emailDO) throws InterruptedException {
        emailBQ.put(emailDO);
    }

    public EmailDO cosume() throws InterruptedException {
        return emailBQ.take();
    }
}
