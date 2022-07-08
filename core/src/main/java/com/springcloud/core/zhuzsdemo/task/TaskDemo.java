package com.springcloud.core.zhuzsdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务DEMO
 * <p>
 * TODO 基于@Scheduled注解和@EnableScheduling注解的单线程实现
 *
 * @author: zhu_zishuang
 * @date: 2020-11-26
 */
@Component
@Slf4j
public class TaskDemo {
    /**
     * 定时任务业务层
     */
    @Autowired
    private TaskDemoService taskDemoService;

    //        @Scheduled(cron = "5 * * * * *")
    public void dailyWrittingTask() {
        log.info("定时任务写作练习，开始执行！");
        taskDemoService.dailyWriting();
        log.info("定时任务写作练习，执行结束！");
    }
}

