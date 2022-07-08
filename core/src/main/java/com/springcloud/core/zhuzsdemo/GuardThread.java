package com.springcloud.core.zhuzsdemo;

/**
 * 线程的分类——守护线程
 * <p>
 * 测试守护线程
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public class GuardThread {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread t1 = new Thread(god);
        Thread t2 = new Thread(you);

        //默认为false表示用户线程，正常的线程都是用户线程
        t1.setDaemon(true);

        t1.start();
        t2.start();

    }
}


//上帝
class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你!");
        }
    }
}


//你
class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("开心的活着！");
        }
        System.out.println("=============GG=============");
    }
}
