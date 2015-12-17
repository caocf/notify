/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.common.factory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.baidu.yangchao.notify.common.conf.GlobalConf;
import com.baidu.yangchao.notify.domain.SmsDO;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class SmsFactory {
    private static final SmsFactory emailFactory = new SmsFactory();
    private static BlockingQueue<SmsDO> smsBQ = new ArrayBlockingQueue<>(GlobalConf.SMS_SEND_MAX);

    private SmsFactory() {
    }

    public static SmsFactory getInstance() {
        return emailFactory;
    }

    public void produce(SmsDO smsDO) throws InterruptedException {
        if (null != smsDO) {
            smsBQ.put(smsDO);
        }
    }

    public SmsDO cosume() throws InterruptedException {
        return smsBQ.take();
    }
}
