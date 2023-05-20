package com.ac.springboot.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 自定义注解
 * //@ResponseBody注解会把返回Object序列化成JSON字符串,
 * 大致就是在序列化前把Object赋值给Result<Object>就可以了
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody // 目的是继承@ResponseBody的功能
public @interface ResultResponseBody {
}
