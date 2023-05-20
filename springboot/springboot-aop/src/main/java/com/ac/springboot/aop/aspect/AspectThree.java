package com.ac.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *  @Description: AspectOne
 *  @author: zhangyadong
 *  @Date: 2020/8/8 19:27
 *  @version: V1.0
 */
@Slf4j
@Aspect
@Component
@Order(3)
public class AspectThree {

    @Pointcut("execution(public * com.ac.spring.aop.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before() {
        log.info("before three");
    }

    @After(value = "pointCut()")
    public void after() {
        log.info("after three");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        log.info("afterReturning three");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("around three start");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("around error",e);
        }
        log.info("around three end");
        return result;
    }
}
