package com.springcloud.core.zhuzsdemo.aop.jdkproxy;

import com.springcloud.core.zhuzsdemo.aop.UserService;
import com.springcloud.core.zhuzsdemo.aop.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * jdk proxy test
 *
 * @author zhu_zishuang
 * @date 6/7/21
 */
public class JdkproxyTest {
    public static void main(String[] args) {
        try {
            Class[] clss = {UserService.class};
            // java Reflection
            Class c1 = clss[0];
            Method[] declaredMethods = c1.getDeclaredMethods();
            Method add = c1.getDeclaredMethod("add", Integer.class, Integer.class);
            Field[] declaredFields = c1.getDeclaredFields();
            Field fieldName = c1.getDeclaredField("fieldName");

            // jdk proxy
            UserService userService = (UserService) Proxy.newProxyInstance(JdkproxyTest.class.getClassLoader(), clss, new JdkProxy(new UserServiceImpl()));
            userService.add(1, 3);
            userService.result("这是第二个方法...");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 自定义代理类
 *
 * @author zhu_zishuang
 * @date 6/7/21
 */
@Slf4j
class JdkProxy implements InvocationHandler {
    /**
     * 被代理类
     */
    private Object obj;

    public JdkProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * 增强逻辑
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info(" 方法前执行，methodName： " + method.getName() + "，参数：" + Arrays.toString(args));
        Object res = method.invoke(obj, args);
        log.info(" 方法后执行，方法输出： " + res);
        return res;
    }
}

