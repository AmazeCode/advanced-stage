package com.ac.springboot.design.create.singleton;

/**
 * 单例-饿汉式
 *  特点：在类加载期间初始化私有静态实例,保证instance实例创建过程是线程安全的
 *  缺点：不支持懒加载，如果对象比较大的话，而且一直没有使用就会造成内存的浪费
 * @Author: zhangyadong
 * @Date: 2022/11/24 17:26
 */
public class Singleton_01 {

    // 1、私有化构造函数
    private Singleton_01(){

    }

    // 2、在本类中创建私有的本类对象
    private static Singleton_01 instance = new Singleton_01();

    // 3、提供一个全局访问点，供外部获取单例对象
    public static Singleton_01 getInstance() {
        return instance;
    }
}
