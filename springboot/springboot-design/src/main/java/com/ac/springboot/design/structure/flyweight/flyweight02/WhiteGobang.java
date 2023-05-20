package com.ac.springboot.design.structure.flyweight.flyweight02;

/**
 * 共享享元类-白色棋子
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:45
 */
public class WhiteGobang extends GobangFlyweight {

    @Override
    public String getColor() {
        return "白色";
    }
}
