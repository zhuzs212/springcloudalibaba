package com.springcloud.sonsumer.demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者 Nacos 实现方式
 *
 * @author zhu_zishuang
 * @date 2022/5/12 18:21
 */
@RestController
@RequestMapping("/consumerNacos")
@Slf4j
public class ConsumerNacosController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @PostMapping("/add")
    public String add() {
//        loadBalancerClient.choose("productor-service");
        log.info("下单成功！");
        String message = restTemplate.getForObject("http://productor-service/productorNacos/reduce", String.class);
        return message;
    }

}
