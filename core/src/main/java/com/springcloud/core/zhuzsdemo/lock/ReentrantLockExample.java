package com.springcloud.core.zhuzsdemo.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 轮询锁 死循环、线程饿死等问题优化
 *
 * @author zhu_zishuang
 * @date 2021-02-24
 */
public class ReentrantLockExample {
    private static Random rdm = new Random();

    public static void main(String[] args) {
        // 创建锁 A
        Lock lockA = new ReentrantLock();
        // 创建锁 B
        Lock lockB = new ReentrantLock();

        // 创建线程 1(使用轮询锁)
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用轮询锁
                pollingLock(lockA, lockB, 3);
            }
        });
        // 运行线程
        t1.start();

        // 创建线程 2
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 加锁
                    lockB.lock();
                    try {
                        System.out.println("线程 2:获取到锁 B!");
                        System.out.println("线程 2:等待获取 A...");
                        // 加锁
                        lockA.lock();
                        try {
                            System.out.println("线程 2:获取到锁 A!");
                        } finally {
                            // 释放锁
                            lockA.unlock();
                        }
                    } finally {
                        // 释放锁
                        lockB.unlock();
                    }
                    // 等待一秒之后继续执行
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start(); // 运行线程
    }

    /**
     * 轮询锁
     */
    public static void pollingLock(Lock lockA, Lock lockB, int maxCount) {
        // 循环次数计数器
        int count = 0;
        while (true) {
            // 尝试获取锁
            if (lockA.tryLock()) {
                System.out.println("线程 1:获取到锁 A!");
                try {
                    // 等待 0.1s(获取锁需要的时间)
                    Thread.sleep(100);
                    System.out.println("线程 1:等待获取 B...");
                    // 尝试获取锁
                    if (lockB.tryLock()) {
                        try {
                            System.out.println("线程 1:获取到锁 B!");
                        } finally {
                            // 释放锁
                            lockB.unlock();
                            System.out.println("线程 1:释放锁 B.");
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lockA.unlock();
                    System.out.println("线程 1:释放锁 A.");
                }
            }

            // 判断是否已经超过最大次数限制
            if (count++ > maxCount) {
                // 终止循环
                System.out.println("轮询锁获取失败,记录日志或执行其他失败策略");
                return;
            }

            // 等待一定时间(固定时间 + 随机时间)之后再继续尝试获取锁
            try {
                // 固定时间 + 随机时间
                Thread.sleep(300 + rdm.nextInt(8) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // 可重入性
        reentrant();
    }

    /**
     * 可重入性
     *
     * @author zhu_zishuang
     * @date 2021-02-24
     */
    public static void reentrant() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            long num = number(24);
            System.out.println("24个月后，兔子的总数量为：" + num);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 有一对兔子，生长三个月后。开始生第一对兔子，并且以后每月生一对兔子，小兔子生长三个月后，也开始生兔子，问N个月后兔子的总数量
     *
     * @param mouth
     * @return N个月后兔子的总数量
     */
    public static long number(int mouth) {
        if (mouth < 0) {
            System.out.println("您输入的数字有误");
        }
        //刚开始和第一、第二个月只有一对兔子
        if (mouth == 1 || mouth == 2 || mouth == 0) {
            return 1;
        } else {
            //前一月兔子数+通过生长三个月后，可以生育的兔[poj
            return number(mouth - 1) + number(mouth - 3);
        }

    }
}
