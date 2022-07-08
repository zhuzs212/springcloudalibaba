package com.springcloud.productor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 生产者
 *
 * @author zhuzishuang
 * @date 2022/5/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductorApplication.class, args);
      }
}
