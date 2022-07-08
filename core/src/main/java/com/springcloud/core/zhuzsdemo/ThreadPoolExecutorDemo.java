package com.springcloud.core.zhuzsdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池四种拒绝策略
 *
 * @author zhu_zishuang
 * @date 2021-03-04
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        //核心线程数
        int corePoolSize = 3;
        //最大线程数
        int maximumPoolSize = 6;
        //超过 corePoolSize 线程数量的线程最大空闲时间
        long keepAliveTime = 2;
        //以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //创建工作队列，用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
        ThreadPoolExecutor threadPoolExecutor = null;
        try {
            //创建线程池
            threadPoolExecutor = new ThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    unit,
                    workQueue,
                    // 丢弃任务并抛出异常
                    new ThreadPoolExecutor.AbortPolicy()
                    // 将被拒绝的任务添加到"线程池正在运行的线程"中去执行
//                    new ThreadPoolExecutor.CallerRunsPolicy()
                    // 直接丢弃，不抛出异常
//                    new ThreadPoolExecutor.DiscardPolicy()
                    // 丢弃队列中最靠前的任务，重新尝试执行任务
//                    new ThreadPoolExecutor.DiscardOldestPolicy()
            );

            //循环提交任务
            for (int i = 0; i < 9; i++) {
                //提交任务的索引
                final int index = (i + 1);
                threadPoolExecutor.submit(() -> {
                    //线程打印输出
                    System.out.println("大家好，我是线程：" + Thread.currentThread().getName() + "；正在执行任务—" + index);
                    try {
                        //模拟线程执行时间，10s
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                //每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}

