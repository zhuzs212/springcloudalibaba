package com.springcloud.core.constant;


import common.BaseEnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 业务异常 枚举
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@AllArgsConstructor
@Accessors(chain = true)
public enum CoreExceptionEnum implements BaseEnumInterface {
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
