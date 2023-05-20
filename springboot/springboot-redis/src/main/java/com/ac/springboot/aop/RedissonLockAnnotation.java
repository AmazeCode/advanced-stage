package com.ac.springboot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁自定义注解
 * @Author: zhangyadong
 * @Date: 2022/10/25 23:18
 */
@Target(ElementType.METHOD) //注解在方法
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLockAnnotation {

    /**
     * 指定组成分布式锁的key
     */
    String lockRedisKey();
}
