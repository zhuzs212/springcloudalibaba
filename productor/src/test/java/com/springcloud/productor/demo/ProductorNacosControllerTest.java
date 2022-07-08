package com.springcloud.productor.demo;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhu_zishuang
 * @date 2022/5/23 17:54
 */
class ProductorNacosControllerTest {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendHelloWorld() {
        SendResult result = rocketMQTemplate.syncSend("operation-topic", "hello world");
    }

}
