package com.springcloud.core.exception;


import exception.ExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常 枚举
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@AllArgsConstructor
public enum CoreExceptionEnum implements ExceptionInterface {
    /* ===========================User====================================== */
    /**
     * 数据不存在
     */
    LIST_EMPTY_EXCEPTION(10000, "数据不存在！"),

    ;

    @Getter
    private final int code;

    @Getter
    private final String message;

}
