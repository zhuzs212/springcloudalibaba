package com.springcloud.core.zhuzsdemo.task;

/**
 *
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public class AliCloudApiRealtimeStaInfoTask {

    public void getRealtimeStaInfo() {

    }

    public void saveRealtimeStaInfo() {

    }

    //    @Scheduled(cron = "* * * * * 1")
    public void realtimeStaInfoTask() {

        getRealtimeStaInfo();

        saveRealtimeStaInfo();

    }

}

