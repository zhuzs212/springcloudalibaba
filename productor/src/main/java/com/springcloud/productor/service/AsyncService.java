package com.springcloud.productor.service;

/**
 * @author zhuzs
 * @date 2022/9/30 13:55
 */
public interface AsyncService {
    /**
     * 同步方法
     *
     * @throws InterruptedException
     */
    void syncData() throws InterruptedException;

    /**
     * 异步方法
     *
     * @throws InterruptedException
     */
    void asyncData() throws InterruptedException;
}
