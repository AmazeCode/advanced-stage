package com.ac.springboot.design.create.singleton;

/**
 * 单例-懒汉式(线程不安全)
 *  特点：延时加载，只有调用getInstance()，才会创建对象
 * @Author: zhangyadong
 * @Date: 2022/11/24 17:36
 */
public class Singleton_02 {

    // 1、私有构造方法
    private Singleton_02(){

    }

    // 2、定义实例对象，不初始化
    private static Singleton_02 instance;

    // 3、提供一个全局访问点，供外部获取单例对象
    public static Singleton_02 getInstance() {
        if (instance == null) {
            // 这里会存在线程安全问题，加入A运行到这里，cup控制权被B抢去,B运行发现实例为创建，就创建实例并返回，
            // 然后A重新获得控制权又创建一个对象返回，造成两次对象不一致
            instance = new Singleton_02();
        }
        return instance;
    }
}
