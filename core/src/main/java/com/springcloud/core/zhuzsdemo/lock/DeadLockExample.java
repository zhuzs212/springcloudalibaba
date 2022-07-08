package com.springcloud.core.zhuzsdemo.lock;

/**
 * 死锁案例
 *
 * @author zhu_zishuang
 * @date 2021-03-04
 */
public class DeadLockExample {
    public static void main(String[] args) {
        // 创建锁 A
        Object lockA = new Object();
        // 创建锁 B
        Object lockB = new Object();
        /**
         * 创建线程 1
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA) {
                    System.out.println("线程 1:获取到锁 A!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程 1:等待获取 B...");
                    synchronized (lockB) {
                        System.out.println("线程 1:获取到锁 B!");
                    }
                }
            }
        });
        // 运行线程
        t1.start();

        /**
         * 创建线程 2
         */
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB) {
                    System.out.println("线程 2:获取到锁 B!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程 2:等待获取 A...");
                    synchronized (lockA) {
                        System.out.println("线程 2:获取到锁 A!");
                    }
                }
            }
        });
        // 运行线程
        t2.start();
    }
}
