package com.springcloud.core.zhuzsdemo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 使用synchronized 实现ReentrantLock(美团面试题目)
 *
 * @author zhu_zishuang
 * @date 2020-11-29
 */
@Slf4j
public class NaiveLock {

    private static final long NONE = -1;
    private long owner = NONE;

    public synchronized void lock() {
        long currentThreadId = Thread.currentThread().getId();
        if (owner == currentThreadId) {
            // 当前线程已获取锁
            throw new IllegalStateException("lock has been acquired by current thread");
        }

        while (this.owner != NONE) {

            log.info("thread %s is waiting lock", currentThreadId);
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 当前线程已获取锁
        this.owner = currentThreadId;
        log.info("lock is acquired by thread %s", currentThreadId);

    }

    public synchronized void unlock() {

        long currentThreadId = Thread.currentThread().getId();

        if (this.owner != currentThreadId) {
            throw new IllegalStateException("Only lock owner can unlock the lock");
        }

        log.info("thread %s is unlocking", owner);
        owner = NONE;
        notify();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final NaiveLock lock = new NaiveLock();
        ExecutorService executor = newFixedThreadPool(20, new ThreadFactory() {
            private ThreadGroup group = new ThreadGroup("test thread group");

            {
                group.setDaemon(true);
            }

            @Override
            public Thread newThread(Runnable r) {
                // TODO Auto-generated method stub
                return new Thread(group, r);
            }
        });

        for (int i = 0; i < 20; i++) {
            executor.submit(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    lock.lock();
                    log.info("thread %s is running...", Thread.currentThread().getId());
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            });
        }
    }

}
