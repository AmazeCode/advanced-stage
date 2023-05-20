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
@Order(2)
public class AspectTwo {

    @Pointcut("execution(public * com.ac.spring.aop.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before() {
        log.info("before two");
    }

    @After(value = "pointCut()")
    public void after() {
        log.info("after two");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        log.info("afterReturning two");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("around two start");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("around error",e);
        }
        log.info("around two end");
        return result;
    }
}
