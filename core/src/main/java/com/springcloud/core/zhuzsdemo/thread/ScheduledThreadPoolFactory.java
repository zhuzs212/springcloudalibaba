package com.springcloud.core.zhuzsdemo.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import exception.ServiceException;
import common.SysBaseEnumEnum;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 工具类
 * 含：周期性线程池、可缓存线程池
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public class ScheduledThreadPoolFactory {
    /**
     * 核心线程数
     * Runtime.getRuntime().availableProcessors():返回可用处理器的Java虚拟机的数量
     */
    public static final Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    /**
     * 最大线程数
     */
    public static final Integer MAX_POOL_SIZE = 20;

    /**
     * 核心线程外的线程最大空闲时间
     */
    public static final Long KEEP_ALIVE_TIME = 60L;

    /**
     * 周期性/延时性任务线程池
     */
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    /**
     * 可缓存线程池
     * <p>
     * 线程池的核心实现
     * 注：
     * 该创建方式，更加明确线程池的运行规则，规避资源耗尽的风险。
     * <p>
     * Executors各个方法的弊端：
     * 1）newFixedThreadPool和newSingleThreadExecutor:
     * 主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
     * 2）newCachedThreadPool和newScheduledThreadPool:
     * 主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
     */
    private final ThreadPoolExecutor threadPoolExecutor;

    /**
     * 构造方法
     */
    private ScheduledThreadPoolFactory() {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
                CORE_POOL_SIZE,
                new ThreadFactoryBuilder().setNamePrefix("scheduled-threadPool-%d").build()
        );
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                CORE_POOL_SIZE * 2,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(MAX_POOL_SIZE),
                new ThreadFactoryBuilder().setNamePrefix("threadPool-%d").build(),
                //拒绝策略——默认方式
                new ThreadPoolExecutor.AbortPolicy()
                // 将被拒绝的任务添加到"线程池正在运行的线程"中去执行
//                    new ThreadPoolExecutor.CallerRunsPolicy()
                // 直接丢弃，不抛出异常
//                    new ThreadPoolExecutor.DiscardPolicy()
                // 丢弃队列中最靠前的任务，重新尝试执行任务
//                    new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }

    /**
     * 枚举单例
     */
    private enum Singleton {
        /**
         * 枚举实例，该实例天生为单例
         */
        INSTANCE;

        private final ScheduledThreadPoolFactory instance;

        Singleton() {
            instance = new ScheduledThreadPoolFactory();
        }
    }

    /**
     * 获取线程池工厂单例
     *
     * @return 线程池工厂对象
     */
    public static ScheduledThreadPoolFactory getInstance() {
        return Singleton.INSTANCE.instance;
    }

    /**
     * 创建线程
     *
     * @param abstractTaskThread 具体线程
     */
    public void createThread(AbstractTaskThread abstractTaskThread) {
        switch (abstractTaskThread.threadTypeEnum) {
            // 不带延时的周期性线程类型
            case TASK_SCHEDULE_AT_FIXED_RATE:
                scheduledThreadPoolExecutor.scheduleAtFixedRate(
                        abstractTaskThread,
                        abstractTaskThread.getInitialDelay(),
                        abstractTaskThread.getPeriod(),
                        TimeUnit.MILLISECONDS
                );
                break;
            // 带延时的周期性线程类型
            case TASK_SCHEDULE_WITH_FIXED_DELAY:
                scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                        abstractTaskThread,
                        abstractTaskThread.getInitialDelay(),
                        abstractTaskThread.getPeriod(),
                        TimeUnit.MILLISECONDS
                );
                break;
            // 延时的任务线程类型
            case TASK_SCHEDULE_DELAY:
                scheduledThreadPoolExecutor.schedule(
                        abstractTaskThread,
                        abstractTaskThread.getInitialDelay(),
                        TimeUnit.MILLISECONDS
                );
                break;
            // 缓存线程类型
            case TASK_CACHED:
                threadPoolExecutor.execute(abstractTaskThread);
                break;
            default:
                throw new ServiceException(SysBaseEnumEnum.CREATE_THREAD_EXCEPTION);
        }

    }

}

