//package com.springcloud.productor.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RefreshScope
//@RestController
//public class BaseController {
//    /**
//     * 获取配置文件中的 cml.age 内容，若未获取到，默认值为18
//     */
//    @Value("${user.age:18}")
//    private String age;
//
//    @GetMapping("/age")
//    public String getAge() {
//        return age;
//    }
//}
