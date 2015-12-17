/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.common.factory;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;

/**
 * 模拟缓存队列
 * <p/>
 * Created by yangchao
 * on 15/12/17.
 */
public class QueueFactory {

    private final static QueueFactory queueFactory = new QueueFactory();
    // 基于链接节点的无界线程安全队列, 用于模拟缓存
    private static ConcurrentLinkedQueue<EmailDO> emailQueue = new ConcurrentLinkedQueue<>();
    private static ConcurrentLinkedQueue<SmsDO> smsQueue = new ConcurrentLinkedQueue<>();

    private QueueFactory() {
    }

    public static QueueFactory getInstance() {
        return queueFactory;
    }

    public void putEmail(EmailDO eo) {
        if (null != eo) {
            emailQueue.add(eo);
        }
    }

    public EmailDO getEmail() {
        /** 获取并移除此队列的头 ，如果此队列为空，则返回 null */
        return emailQueue.poll();
    }

    public void putSms(SmsDO so) {
        if (null != so) {
            smsQueue.add(so);
        }
    }

    public SmsDO getSms() {
        return smsQueue.poll();
    }
}
