/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.yangchao.notify.common.conf;

/**
 * 通知方式枚举类
 * Created by yangchao
 * on 15/12/14.
 */
public enum NotifyEnum {
    EMAIL(0), SMS(1), HI(1);

    private int value;

    NotifyEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
