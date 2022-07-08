package com.springcloud.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 基于完全注解开发的 AOP配置
 *
 * 注意：proxyTargetClass：这个属性，true代表使用的是CGLIB代理无接口类；false为默认值，使用的是JDK代理有接口类。
 *
 * @author zhuzishuang
 * @date 2022/5/18
 */
@Configuration
@ComponentScan(basePackages = "com.springcloud.core")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
}

