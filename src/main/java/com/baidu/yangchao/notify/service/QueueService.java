/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.service;

import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public interface QueueService {

    void importEmail(EmailDO emailDO) throws InterruptedException;

    void importSms(SmsDO smsDO) throws InterruptedException;

}
