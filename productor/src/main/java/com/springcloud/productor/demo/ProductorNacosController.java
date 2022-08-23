package com.springcloud.productor.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者 Nacos 实现方式
 *
 * @author zhu_zishuang
 * @date 2022/5/12 18:23
 */
@RestController
@RequestMapping("/productorNacos")
public class ProductorNacosController {

    @Value("${server.port:8802}")
    private String port;

    @RequestMapping("/reduce")
    public String reduce() {
        return "动态配置服务端口！port: " + port;
    }
}


