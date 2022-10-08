package com.springcloud.productor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 生产者
 *
 * @author zhuzishuang
 * @date 2022/5/12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
public class ProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductorApplication.class, args);
      }
}
