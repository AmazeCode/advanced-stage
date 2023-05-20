package com.ac.springboot.design.create.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

/**
 * 双重验证测试
 * @Author: zhangyadong
 * @Date: 2022/11/24 22:29
 */
@SpringBootTest
public class Singleton_04Test {

    @Test
    public void singleton_04Test() {
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                Singleton_04 instance = Singleton_04.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    /**
     * 验证序列化对单例的破坏
     */
    @Test
    public void singleton_DoubleTest() throws Exception {

        // 序列化对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile.obj"));
        oos.writeObject(Singleton_Double.getInstance());

        // 序列化对象输入流
        File file = new File("tempFile.obj");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 序列化破坏单例的问题所在点
        Singleton_Double singleton = (Singleton_Double)ois.readObject();

        System.out.println(singleton);
        System.out.println(Singleton_Double.getInstance());

        System.out.println(Singleton_Double.getInstance() == singleton);
    }
}

class Singleton_Double implements Serializable {

    // 1、私有化构造方法
    private Singleton_Double() {
    }

    // 2、在本类中创建私有静态全局对象
    private volatile static Singleton_Double instance;

    // 3、获取单例对象的静态方法
    public static Singleton_Double getInstance() {
        // 第一次判断，如果instance不为null，不进入枪锁阶段，直接返回实例
        if (instance == null) {
            synchronized (Singleton_04.class) {
                // 第二次判断，抢到锁之后再次进行判断，判断是否为null
                if (instance == null) {
                    instance = new Singleton_Double();
                    /*
                        上面的创建对象的代码,在JVM中被分为三步：
                        1、分配内存空间
                        2、初始化对象
                        3、将instance指向分配号的内存空间

                        JVM可能会对上面的步骤进行指令重排序：
                        步骤可能会编程1，3，2 导致对象创建不完成，解决方式让程序按照顺序执行
                     */
                }
            }
        }
        return instance;
    }

    /*
        解决序列化对单列的破坏
        只需要在单例类中定义readResolve方法，就可以解决序列化对单例的破坏
        程序会判断是否有readResolve方法,如果有就执行该方法，如果不存在就会创建新对象
     */
    private Object readResolve() {
        return instance;
    }
}