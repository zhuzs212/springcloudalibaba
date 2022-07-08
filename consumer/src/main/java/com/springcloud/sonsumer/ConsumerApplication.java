package com.springcloud.sonsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhu_zishuang
 * @date 2022/5/12 18:16
 */
@SpringBootApplication(scanBasePackages = {"com.springcloud.sonsumer","com.duolian.fls.payment.client.*"})
@EnableFeignClients(basePackages = "com.duolian.fls.payment.client.feign")
@EnableDiscoveryClient
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
