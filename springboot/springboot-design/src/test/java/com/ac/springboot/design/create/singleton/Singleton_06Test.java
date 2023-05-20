package com.ac.springboot.design.create.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/24 23:26
 */
@SpringBootTest
public class Singleton_06Test {

    /*
        验证枚举是否可以阻止反射对单例的破坏
     */
    @Test
    public void reflectTest() throws Exception {

        // 测试反射对单例的破话
        Class<Singleton_06> clazz = Singleton_06.class;
        Constructor<Singleton_06> constructor= clazz.getDeclaredConstructor();
        constructor.setAccessible(true); // 设置为true后，就可以对类中的私有成员进行操作
        // 无参构造
        //Singleton_06 instance = constructor.newInstance();
        // 带两个参数的构造
        Singleton_06 instance = constructor.newInstance(String.class,Integer.class);

        System.out.println(instance);
    }

    /*
        测试枚举是否可以阻止序列化对单例的破坏
     */
    @Test
    public void serializerTest() throws Exception {

        // 序列化对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile.obj"));
        oos.writeObject(Singleton_06.getInstance());

        // 序列化对象输入流
        File file = new File("tempFile.obj");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 序列化破坏单例的问题所在点
        Singleton_06 singleton = (Singleton_06)ois.readObject();

        System.out.println(singleton);
        System.out.println(Singleton_06.getInstance());

        System.out.println(Singleton_06.getInstance() == singleton);
    }
}
