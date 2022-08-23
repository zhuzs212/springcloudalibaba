package com.springcloud.productor.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhu_zishuang
 * @date 2022/7/6 14:14
 */
@Slf4j
@RestController
// 动态修改 nacos 配置文件，实现变量的热加载
@RefreshScope
@RequestMapping("nacosConfigRead")
public class NacosConfigReadController {
    @Value("${address}")
    private String address;

    @RequestMapping("/getAddress")
    public String getAddress(){
        log.info("address: {}", address);
        return address;
    }
}
