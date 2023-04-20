package com.springcloud.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author zhu_zishuang
 * @date 2022/5/19 14:06
 */
@SpringBootApplication(scanBasePackages = {"com.springcloud.core"})
@EnableAsync
@MapperScan("com.springcloud.core.mapper")
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
