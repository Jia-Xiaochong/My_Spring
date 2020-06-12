package com.jiaxc.dao;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// Spring AOP
// 需要实现什么通知，就实现什么接口
// 这里实现“环绕通知”
public class MyAspect implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("方法执行之前");
        Object obj = invocation.proceed();  // 执行目标方法
        System.out.println(obj);
        System.out.println("方法执行之后");
        return obj;
    }
}
