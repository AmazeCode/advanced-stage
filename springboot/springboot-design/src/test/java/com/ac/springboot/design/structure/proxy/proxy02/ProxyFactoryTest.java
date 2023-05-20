package com.ac.springboot.design.structure.proxy.proxy02;

import com.ac.springboot.design.structure.proxy.proxy02.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂-动态的生成代理对象
 * @Author: zhangyadong
 * @Date: 2022/12/8 17:05
 */
@SpringBootTest
public class ProxyFactoryTest {

    @Test
    public void proxyFactoryTest() {

        IUserDao userDao = new UserDaoImpl();
        System.out.println(userDao.getClass());// 目标对象的信息

        IUserDao proxy = (IUserDao)new ProxyFactory(userDao).getProxyInstance();
        System.out.println(proxy.getClass());// 获取代理对象
        proxy.save();// 代理方法
    }
}
