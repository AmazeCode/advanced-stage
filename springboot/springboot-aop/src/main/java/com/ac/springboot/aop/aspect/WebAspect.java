package com.ac.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *  @Description: 切面WebAspect
 *  @author: zhangyadong
 *  @Date: 2020/8/8 17:22
 *  @version: V1.0
 */
@Slf4j
@Aspect
@Component
public class WebAspect {

    @Pointcut("execution(public * com.ac.spring.aop.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }

        log.info("path：{}",request.getServletPath());
        log.info("class name：{}",className);
        log.info("method name：{}",methodName);
        log.info("args：{}",paramMap.toString());
    }

    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("{} after", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCut()", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
        log.info("{} after return, returnVal: {}", joinPoint.getSignature().getName(), returnVal);
    }

    /**
     * @Description 异常处理
     * @params [joinPoint, e]
     * @return void
     * @author zhangyadong
     * @date 2020/8/8 17:28
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint  joinPoint, Exception e) {
        log.info("{} after throwing, message: {}", joinPoint.getSignature().getName(), e.getMessage());
    }

    /**
     * @Description 环绕
     * @params [joinPoint]
     * @return java.lang.Object
     * @author zhangyadong
     * @date 2020/8/8 17:28
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("around error",e);
        }
        long endTime = System.currentTimeMillis();
        log.info("execute time：{} ms",endTime - startTime);
        return result;
    }
}
