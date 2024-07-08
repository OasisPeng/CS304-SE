package com.example.cs304.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceMonitorAspect {
    @Pointcut("execution(* com.example.cs304.service..*.*(..)) || @annotation(com.example.cs304.annotation.MonitorPerformance)")
    public void cut(){}
    @Around(value = "cut()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();  // 执行目标方法
        
        long elapsedTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " executed in " + elapsedTime + " ms");
        
        return result;
    }
}
