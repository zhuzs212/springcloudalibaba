package com.springcloud.core.zhuzsdemo.aop;

/**
 * jdk proxy service demo
 *
 * @author zhu_zishuang
 * @date 6/7/21
 */
public interface UserService {
    int add(int a, int b);

    String result(String info);
}
