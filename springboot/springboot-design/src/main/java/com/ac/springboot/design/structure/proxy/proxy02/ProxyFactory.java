package com.ac.springboot.design.structure.proxy.proxy02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂-动态的生成代理对象
 * @Author: zhangyadong
 * @Date: 2022/12/8 17:05
 */
public class ProxyFactory {

    // 维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance() {

        return Proxy.newProxyInstance(
                // 目标类使用的类加载器
                target.getClass().getClassLoader(),
                // 目标对象实现的接口类型
                target.getClass().getInterfaces(),
                // 重要
                new InvocationHandler() {// 事件处理器
                    /**
                     * @param: proxy   代理对象
                     * @param: method  对应于在代理对象上调用的接口方法实例
                     * @param: args    对应了代理对象在调用接口方法时传递的实际参数
                     * @return: java.lang.Object  返回目标对象的方法的返回值，没有返回值就返回null
                     * @author: zhangyadong
                     * @date: 2022/12/8 17:19
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务");
                        method.invoke(target,args);
                        System.out.println("提交事务");
                        return null;
                    }
                }
        );
    }

}