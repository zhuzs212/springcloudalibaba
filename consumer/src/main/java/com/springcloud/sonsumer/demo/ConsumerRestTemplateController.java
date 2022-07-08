package com.springcloud.sonsumer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhu_zishuang
 * @date 2022/5/12 18:21
 */
@RestController
@RequestMapping("/consumerRestTemplate")
public class ConsumerRestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public String add() {
        System.out.println("下单成功！");
        String message = restTemplate.getForObject("http://localhost:8802/ProductorRestTemplate/reduce", String.class);
        return message;
    }

    public static void main(String[] args) {
        // 数组
        Boolean[] booleans = new Boolean[12];
        booleans[0] = true;
        booleans[2] = false;
        System.out.println(Arrays.toString(booleans));

        int[] intArr = new int[2];
        intArr[0] = 1;
        intArr[1] = 100;

        // 集合
        // 1.基本类型包装类
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

    }
}
