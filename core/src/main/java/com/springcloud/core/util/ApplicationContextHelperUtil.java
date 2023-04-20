package com.springcloud.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 此工具类用于从Spring的上下文中去获取到类，解决@autowird注入空指针的问题 TODO（1.不能在定时任务中调用别的类 2.不能new一个类。）
 *
 * 初始化顺序： 成员变量初始化 -> Constructor -> @Autowired
 * @author zhuzs
 * @date 2022/7/27 10:25
 */
@Component
public class ApplicationContextHelperUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext( ApplicationContext applicationContext1 ) throws BeansException {
        applicationContext = applicationContext1;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }
}