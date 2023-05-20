package com.ac.springboot.design.structure.proxy.proxy03;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * cglib 扩展打印日志方法
 * 步骤：1、引入cglib依赖
 * 2、代理类实现MethodInterceptor，并实现intercept方法
 * @Author: zhangyadong
 * @Date: 2022/12/9 11:35
 */
public class UserLogProxy implements MethodInterceptor {

    /**
     * 生成cglib动态代理类
     * @param target 代理的目标对象
     * @return       返回代理类对象
     */
    public Object getLogProxy(Object target) {
        // 增强器类,用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类字节码对象
        enhancer.setSuperclass(target.getClass());
        // 设置回调 (回调在哪就设置哪个类)
        enhancer.setCallback(this);

        // 创建动态代理对象，并返回
        return enhancer.create();
    }


    /**
     * @description: 实现回调方法
     * @param: o    代理对象
     * @param: method  目标对象中的方法的Method实例
     * @param: args 实现参数
     * @param: methodProxy 代理类对象中的方法的Method实例
     * @return: java.lang.Object
     * @author: zhangyadong
     * @date: 2022/12/9 11:44
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(format.format(calendar.getTime()) + "[" + method.getName() + "] 查询用户信息。。。。。");

        Object result = methodProxy.invokeSuper(o,args);
        return result;
    }
}
