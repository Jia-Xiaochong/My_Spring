package com.jiaxc.dao;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

// 基于 XML
public class Logging {
    /**
     * 前置通知
     */
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("前置通知");
    }
    /**
     * 环绕通知
     */
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕开始"); // 开始
        Object obj = proceedingJoinPoint.proceed(); // 执行当前目标方法
        System.out.println("环绕结束"); // 结束
        return obj;
    }
    /**
     * 后置通知
     */
    public void afterReturningAdvice(Object retVal){
        System.out.println("后置通知，返回值:" + retVal.toString() );
    }
    /**
     * 异常通知
     */
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("异常通知: " + ex.toString());
    }
    /**
     * 最终通知
     */
    public void afterAdvice(){
        System.out.println("最终通知");
    }
}
