/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.yangchao.notify.domain.EmailDO;
import com.baidu.yangchao.notify.domain.SmsDO;
import com.baidu.yangchao.notify.domain.http.BaseResponse;
import com.baidu.yangchao.notify.domain.http.NotifyRequest;
import com.baidu.yangchao.notify.service.QueueService;

/**
 * Created by yangchao
 * on 15/12/14.
 */
@Controller
@RequestMapping(value = "/notify")
public class NotifyApiController {
    private static Logger logger = Logger.getLogger(NotifyApiController.class);

    @Autowired
    private QueueService queueService;

    @ResponseBody
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    BaseResponse<?> notify(@RequestBody NotifyRequest request) {
        if (!check(request)) {
            return BaseResponse.failKBResponse("check fail");
        }

        try {
            int type = request.getSendType();
            switch (type) {
                case 0:
                    final EmailDO emailDO = EmailDO.build(request);
                    queueService.importEmail(emailDO);
                    break;
                case 1:
                    final SmsDO smsDO = SmsDO.build(request);
                    queueService.importSms(smsDO);
                    break;
                // todo hi send
                // case 2:
                default:
                    throw new Exception("send type is error");
            }
            return BaseResponse.succKBResponse(null);
        } catch (Exception e) {
            logger.error("notify request meet exception", e);
            return BaseResponse.failKBResponse("check fail");
        }
    }

    private boolean check(NotifyRequest request) {
        if (null != request && null != request.getSendType()) {
            return true;
        }
        return false;
    }
}