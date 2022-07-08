package com.springcloud.core.zhuzsdemo.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * Cglib 优点：
 * - jdk动态代理只能基于接口，代理生成的对象只能赋值给接口变量，
 *   Cglib是通过生成子类来实现的，代理对象既可以赋值给实现类，又可以赋值给接口；
 * - Cglib速度比jdk动态代理更快，性能更好；
 * - 通过字节码技术动态创建子类实例;
 * - springboot从2.X开始也是基于Cglib动态代理实现；
 *
 * @author zhuzishuang
 * @date 2022/5/18
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    private Object bean;

    public CglibProxy(Object bean) {
        this.bean = bean;
    }

    public Object getProxy(){
        //设置需要创建子类的类
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }
    //实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals("add")){
            System.out.println("add~~~");
        }else if(methodName.equals("result")){
            System.out.println("result~~~");
        }

        //调用原bean的方法
        return method.invoke(bean,args);
    }
}
