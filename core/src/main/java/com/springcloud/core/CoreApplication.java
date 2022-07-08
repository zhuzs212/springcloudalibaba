package com.springcloud.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author zhu_zishuang
 * @date 2022/5/19 14:06
 */
@SpringBootApplication(scanBasePackages = {"com.springcloud.core"})
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
