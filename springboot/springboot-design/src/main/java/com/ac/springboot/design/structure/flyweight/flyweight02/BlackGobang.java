package com.ac.springboot.design.structure.flyweight.flyweight02;

/**
 * 共享享元类-黑色棋子
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:46
 */
public class BlackGobang extends GobangFlyweight{

    @Override
    public String getColor() {
        return "黑色";
    }
}
