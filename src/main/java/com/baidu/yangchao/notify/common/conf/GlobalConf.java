/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.common.conf;

/**
 * Created by yangchao
 * on 15/12/17.
 */
public class GlobalConf {
    // 每次批量执行最大发送上限
    public static Integer EMAIL_SEND_MAX = 1000;
    public static Integer SMS_SEND_MAX = 1000;
    // 最大线程池配置
    public static Integer MAX_THREADPOOL = 10;

}
