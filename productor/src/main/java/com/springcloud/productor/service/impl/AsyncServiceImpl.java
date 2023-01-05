package com.springcloud.productor.service.impl;

import com.springcloud.productor.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zhuzs
 * @date 2022/9/30 13:55
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    @Lazy
    private AsyncService asyncService;

    @Override
    public void syncData() throws InterruptedException {
        log.info("【同步方法】，线程名称: {}", Thread.currentThread().getName());
        asyncService.asyncData();
        asyncData1();
    }

    @Override
    @Async
    public void asyncData() throws InterruptedException {
        Thread.sleep(50000);
        log.info("【异步方法】，线程名称: {}", Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            log.info("正在执行异步逻辑。。。");
        }
    }


    /**
     * 此方式，异步不生效
     *
     * @throws InterruptedException
     */
    @Async
    public void asyncData1() throws InterruptedException {
        Thread.sleep(50000);
        log.info("【异步方法1】，线程名称: {}", Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            log.info("正在执行异步逻辑。。。");
        }
    }
}
