package com.springcloud.core.zhuzsdemo.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhu_zishuang
 * @date 7/2/21
 */
@RestController
@RequestMapping("/redisDemo")
public class RedisDemo {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 票据库存扣除
     *
     * @param
     * @return
     */
    @GetMapping("/deductStock")
    public String deductStock() {
        synchronized (this) {
            int stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(realStock));
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足！");
            }
            return "end";
        }
    }
}

