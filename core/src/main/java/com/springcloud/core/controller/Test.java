package com.springcloud.core.controller;

import com.springcloud.core.exception.CoreExceptionEnum;
import exception.ServiceException;

/**
 * @author zhu_zishuang
 * @date 2022/5/12 17:58
 */
public class Test {

    public static void main(String[] args) {
        throw new ServiceException(CoreExceptionEnum.LIST_EMPTY_EXCEPTION);
    }
}
