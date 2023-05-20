package com.ac.springboot.design.create.singleton;

/**
 * 单例-懒汉式(synchronized)
 *  特点：延时加载，synchroized加在方法上保证线程安全，但是加在方法上锁的粒度比较大,会导致并发很低
 * @Author: zhangyadong
 * @Date: 2022/11/24 17:49
 */
public class Singleton_03 {

    // 1、私有构造方法
    private Singleton_03() {
    }

    // 2、定义静态实例参数
    private static Singleton_03 instance;

    // 3、提供一个全局的访问点，供外部获取单例对象
    // 通过synchronized保证线程安全
    public synchronized static Singleton_03 getInstance() {
        if (instance == null) {
            instance = new Singleton_03();
        }
        return instance;
    }
}
