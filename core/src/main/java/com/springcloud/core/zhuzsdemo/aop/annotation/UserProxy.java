package com.springcloud.core.zhuzsdemo.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
//@Order(1)// 数值越小，优先级越高
public class UserProxy {

    // 抽取公共切入点
    @Pointcut(value = "execution(* com.springcloud.core.zhuzsdemo.aop.UserServiceImpl.add(..))")
    public void addPointcut() {
    }

    // 前置通知
    // 使用@Before 注解表示作为前置通知
    @Before(value = "addPointcut()")
    public void before() {
        System.out.println("【前置通知】User代理类触发...");
    }

    // 最终通知，方法无论是否正常返回都执行
    // 使用@After 注解表示作为后置通知
    @After(value = "addPointcut()")
    public void after() {
        System.out.println("【最终通知】触发...");
    }

    // 后置通知，只有方法正常返回之后才执行
    // 使用@AfterReturning 注解表示作为后置通知
    @AfterReturning(value = "addPointcut()")
    public void afterReturning() {
        System.out.println("【后置通知】触发...");
    }

    // 异常通知
    // 使用@AfterThrowing 注解表示作为异常通知
    @AfterThrowing(value = "addPointcut()")
    public void afterThrowing() {
        System.out.println("【异常通知】触发...");
    }

    // 环绕通知
    // 使用@Around 注解表示作为环绕通知
    @Around(value = "addPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("【环绕通知】触发：核心方法之前...");

        // 执行被增强的核心方法
        Object obj=proceedingJoinPoint.proceed();

        System.out.println("【环绕通知】触发：核心方法之后...");
        return obj;
    }

}
