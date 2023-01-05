package com.springcloud.productor.demo.Async;

import com.springcloud.productor.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO @EnableAsync & @Async 实现异步逻辑
 * @author zhuzs
 * @date 2022/9/30 13:55
 */
@RestController
@RequestMapping("/asyncDemo")
@Slf4j
public class AsyncDemoController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/syncData")
    public String test() {
        try {
            Thread.sleep(1000);
            System.out.println("主线程开始");
            for (int j = 0; j < 10; j++) {
                log.info("正在执行主流程。。。");
            }
            asyncService.syncData();
            log.info("主线程结束");
            return "sync";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
