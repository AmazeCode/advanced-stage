package com.ac.springboot.design.create.singleton;

/**
 * 单例-双重校验
 *  特点：延迟加载，不整个方法加锁，提高并发
 * @Author: zhangyadong
 * @Date: 2022/11/24 18:03
 */
public class Singleton_04 {

    // 1、私有化构造方法
    private Singleton_04() {
    }

    // 2、在本类中创建私有静态全局对象
    // 使用 volatile保证变量的可见性，屏蔽指令重排序
    private volatile static Singleton_04 instance;

    // 3、获取单例对象的静态方法
    public static Singleton_04 getInstance() {
        // 第一次判断，如果instance不为null，不进入枪锁阶段，直接返回实例
        if (instance == null) {
            synchronized (Singleton_04.class) {
                // 第二次判断，抢到锁之后再次进行判断，判断是否为null
                if (instance == null) {
                   instance = new Singleton_04();
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
}
