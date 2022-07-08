package com.springcloud.productor.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者
 *
 * @author zhu_zishuang
 * @date 2022/5/12 18:23
 */
@RestController
@RequestMapping("/ProductorRestTemplate")
public class ProductorRestTemplateController {

    @RequestMapping("/reduce")
    public String reduce() {
        return "扣减库存成功！";
    }


}
