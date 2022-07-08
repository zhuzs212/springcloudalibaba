package com.springcloud.core.zhuzsdemo;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * 阻塞队列的基本使用
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
@Slf4j
public class BlockingQueue {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);

        producer.start();
        consumer.start();


    }
}

/**
 * 消费者
 *
 * @author zhu_zishuang
 * @date 2020-11-27
 */
@Slf4j
class Consumer extends Thread {
    private ArrayBlockingQueue<Integer> queue;

    public Consumer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        queue.clear();
        while (true) {
            Integer i = null;
            try {
                i = queue.take();
                log.info("消费者从队列取出元素:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 生产者
 *
 * @author zhu_zishuang
 * @date 2020-11-27
 */
@Slf4j
class Producer extends Thread {
    private ArrayBlockingQueue<Integer> queue;

    public Producer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        queue.clear();
        for (int i = 0; i < 100; i++) {
            try {
                queue.put(i);
                if (i == 50) {
                    log.info("------" + LocalDateTime.now() + "sleep 50S，开始！------");
                    Thread.sleep(50000);
                    log.info("------" + LocalDateTime.now() + "sleep 50S，结束！------");
                }
                log.info("生产者向队列插入元素:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
