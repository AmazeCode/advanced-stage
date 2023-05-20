package com.ac.springboot.design.create.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/24 22:43
 */
@SpringBootTest
public class Singleton_05Test {

    @Test
    public void singleton_05Test() throws Exception{
        // 测试反射对单例的破话
        Class<Singleton_05> clazz = Singleton_05.class;
        Constructor<Singleton_05> constructor= clazz.getDeclaredConstructor();
        constructor.setAccessible(true); // 设置为true后，就可以对类中的私有成员进行操作
        Singleton_05 instance1 = constructor.newInstance();
        Singleton_05 instance2 = constructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}
