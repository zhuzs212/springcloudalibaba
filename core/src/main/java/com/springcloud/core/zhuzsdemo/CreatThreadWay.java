package com.springcloud.core.zhuzsdemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
 * 线程的分类 & 创建方式
 * <p>
 * 1.分类
 * - java中线程分为：用户线程和守护线程。通过Thread.setDaemon(true)设置为守护线程，默认为用户线程；
 * - 主线程结束后，用户线程还会继续运行,JVM存活；
 * - 如果没有用户线程，都是守护线程，那么JVM结束；
 * - 如：后台记录操作日志、监控内存、垃圾回收等待…都是守     护线程
 * <p>
 *
 * <p>
 * 2.状态
 * - 新建(NEW)：新创建了一个线程对象；
 * - 可运行(RUNNABLE)：线程对象创建后，其他线程(比如main线程)调用了该对象的start()方法。
 * 该状态的线程位于可运行线程池中，等待被线程调度选中，获取cpu的使用权 ；
 * - 运行(RUNNING)：可运行状态(runnable)的线程获得了cpu 时间(timeslice)， 执行程序代码;
 * - 阻塞(BLOCKED)：阻塞状态是指线程因为某种原因放弃了cpu 使用权，也即让出了cpu timeslice，暂时停止运行。
 * 直到线程进入可运行(runnable)状态，才有机会再次获得cpu timeslice 转到运行(running)状态。
 * 阻塞的情况分三种：
 * - 等待阻塞：运行(running)的线程执行Object.wait()方法，JVM会把该线程放入等待队列(waitting queue)中;
 * - 同步阻塞：运行(running)的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池(lock pool)中;
 * - 其他阻塞：运行(running)的线程执行Thread.sleep(long ms)或t.join()方法，或者发出了I/O请求时，
 * JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入可运行(runnable)状态.
 * <p>
 *
 * <p>
 * 3.创建方式
 * - 继承Thread类
 * - 实现Runnable接口
 * - 由于类的单继承多实现特性，推荐通过实现接口的方式创建线程
 * <p>
 *
 * <p>
 * - Runnable 和 Callable 有什么不同？
 * - Runnable 从 JDK1.0 开始就有了，Callable 是在 JDK1.5增加的；
 * - Callable 接口下的方法是 call()，Runnable 接口的方法是 run()；
 * - Callable 的任务执行后可返回值，而 Runnable 的任务是不能返回值的；
 * - call() 方法可以抛出异常，run()方法不可以的；
 * - Callable 可以返回装载有计算结果的 Future 对象；
 * 注：
 * - FutureTask 实现了 Runnable 和 Future，所以兼顾两者优点；
 * - FutureTask 可以准确地知道线程什么时候执行完成并获得到线程执行完成后返回的结果；
 * - FutureTask 是一种可以取消的异步的计算任务，它的计算是通过 Callable 实现的，它等价于可以携带结果的 Runnable；
 * - 有三个状态：等待、运行和完成。完成包括所有计算以任意的方式结束，包括正常结束、取消和异常
 *
 * @author zhu_zishuang
 * @date 2021-03-05
 */
public class CreatThreadWay {
    public static void main(String[] args) throws Exception {
        // 实例化继承Thread接口的MyThread类
        MyThreadO mt1 = new MyThreadO("线程一");
        MyThreadO mt2 = new MyThreadO("线程二");
        MyThreadO mt3 = new MyThreadO("线程三");
        // 多线程启动
        new Thread(mt1).start();
        new Thread(mt2).start();
        new Thread(mt3).start();

        // 实例化继承Runnable接口的MyThread类
        Runnable mt4 = new MyThreadT("线程四");
        Runnable mt5 = new MyThreadT("线程五");
        Runnable mt6 = new MyThreadT("线程六");
        // 多线程启动
        new Thread(mt4).start();
        new Thread(mt5).start();
        new Thread(mt6).start();

        // 实例化继承Callable接口的MyThread类
        MyThreadC mt7 = new MyThreadC("线程七");
        MyThreadC mt8 = new MyThreadC("线程八");
        MyThreadC mt9 = new MyThreadC("线程九");

        // FutureTask类接收继承Callable接口的MyThread的实例
        FutureTask<Integer> ft1 = new FutureTask<Integer>(mt7);
        FutureTask<Integer> ft2 = new FutureTask<Integer>(mt8);
        FutureTask<Integer> ft3 = new FutureTask<Integer>(mt9);
        // 启动多线程
        new Thread(ft1).start();
        new Thread(ft2).start();
        new Thread(ft3).start();
        System.out.println(ft1.get());
        System.out.println(ft2.get());
        System.out.println(ft3.get());

    }


    /**
     * 通过CallAble接口创建线程
     * <p>
     * 注：
     * - FutureTask 实现了 Runnable 和 Future；
     */
    public static void callableWay() {
        Callable<String> callable = () -> "个人博客：sunfusheng.com";

        FutureTask<String> task = new FutureTask<String>(callable);

        Thread t = new Thread(task);
        // 启动线程
        t.start();
        // 取消线程
        task.cancel(true);
    }

}

/**
 * 继承Thread类
 *
 * @author zhu_zishuang
 * @date 2021-03-05
 */
class MyThreadO extends Thread {
    private String name;

    public MyThreadO(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(this.name + " 正在工作中……" + i);
        }
    }
}


/**
 * 继承Runnable类
 *
 * @author zhu_zishuang
 * @date 2021-03-05
 */
class MyThreadT implements Runnable {
    private String name;

    public MyThreadT(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(this.name + " 正在执行中……" + i);
        }
    }
}

/**
 * @author zhu_zishuang
 * @date 2021-03-05
 */
class MyThreadC implements Callable<Integer> {
    private String name;

    public MyThreadC(String name) {
        this.name = name;
    }

    @Override
    public Integer call() {
        Integer sum = 0;
        for (int i = 0; i < 500; i++) {
            System.out.println(this.name + i);
            sum += i;
        }
        return sum;
    }
}
