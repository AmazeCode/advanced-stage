package com.ac.springboot.design.structure.flyweight.flyweight02;

/**
 * 抽象享元类：五子棋
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:43
 */
public abstract class GobangFlyweight {

    public abstract String getColor();

    public void display() {
        System.out.println("棋子颜色：" + this.getColor());
    }
}
