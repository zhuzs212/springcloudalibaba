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
@RefreshScope
@RequestMapping("nacosConfigRead")
public class NacosConfigReadController {
    @Value("${address}")
    private String address;

    /**
     * 测试nacos 配置中心，自动刷新配置
     * @return
     */
    @RequestMapping("/getAddress")
    public String getAddress(){
        log.info("address: {}", address);
        return address;
    }

}
