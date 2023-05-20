package com.ac.springboot.design.structure.flyweight.flyweight02;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类-生产五子棋棋子
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:48
 */
public class GobangFactory {

    // 享元池
    private static Map<String, GobangFlyweight> pool;

    // 创建共享享元对象，设置对象的内部状态
    private GobangFactory() {
        pool = new HashMap<>();
        GobangFlyweight black = new BlackGobang();// 黑子
        GobangFlyweight white = new WhiteGobang();// 白子

        pool.put("b",black);
        pool.put("w",white);
    }
    // 获取唯一享元工厂对象的方法
    public static GobangFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 静态内部类
    private static class SingletonHolder{
        private static final GobangFactory INSTANCE = new GobangFactory();
    }

    // 通过key获取结合中的享元对象
    public GobangFlyweight getGobang(String key) {
        return pool.get(key);
    }
}
