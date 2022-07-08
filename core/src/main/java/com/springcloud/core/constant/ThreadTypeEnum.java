package com.springcloud.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 线程类型枚举类
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
@AllArgsConstructor
public enum ThreadTypeEnum {
    /**
     * 不带延时的周期性线程类型
     */
    TASK_SCHEDULE_AT_FIXED_RATE("01"),
    /**
     * 带延时的周期性线程类型
     */
    TASK_SCHEDULE_WITH_FIXED_DELAY("02"),
    /**
     * 延时的任务线程类型
     */
    TASK_SCHEDULE_DELAY("03"),
    /**
     * 缓存线程类型
     */
    TASK_CACHED("04");

    @Getter
    private final String threadType;
}
