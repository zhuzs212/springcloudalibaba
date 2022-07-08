//package com.springcloud.sonsumer.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.ConsumeMode;
//import org.apache.rocketmq.spring.annotation.MessageModel;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author zhuzishuang
// * @date 2022/5/23
// */
//@Component
//@RocketMQMessageListener
//        (
//                //topic主题
//                topic = "operation-topic",
//                //消费组
//                consumerGroup = "consumer-group",
//                messageModel = MessageModel.CLUSTERING,
//                consumeMode = ConsumeMode.ORDERLY
//        )
//@Slf4j
//public class ConsumerListener implements RocketMQListener<String> {
//
//    @Override
//    public void onMessage(String message) {
//        log.info("接受到消息：{}", message);
//    }
//}
