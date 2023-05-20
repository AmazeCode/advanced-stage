package com.ac.springboot.design.create.singleton;

/**
 * 单例-静态内部类(懒加载)
 * 根据静态内部类的特性,同时解决了延迟加载，线程安全，并且代码更简洁
 * @Author: zhangyadong
 * @Date: 2022/11/24 22:34
 */
public class Singleton_05 {

    // 1、私有化构造方法
    private Singleton_05() {
        // 解决反射破坏单例的问题，但是这种方式破坏了单例的简洁性
        /*if (SingletonHandler.instance != null) {
            throw new RuntimeException("不允许非法访问！");
        }*/
    }

    // 2、创建静态内部类
    private static class SingletonHandler{
        // 在静态内部类创建单例对象，在装载内部类的时候，才会创建单例对象
        private static Singleton_05 instance = new Singleton_05();
    }

    // 3、提供一个全局访问点，供外部获取单例对象
    public static Singleton_05 getInstance() {
        return SingletonHandler.instance;
    }
}
