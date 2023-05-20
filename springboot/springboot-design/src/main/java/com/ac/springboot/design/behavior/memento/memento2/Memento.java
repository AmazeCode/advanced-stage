package com.ac.springboot.design.behavior.memento.memento2;

import java.util.ArrayList;
import java.util.List;

/**
 * 备份玩家的状态（备忘录类），限制只在同包下访问
 * @Author: zhangyadong
 * @Date: 2022/12/25 11:54
 */
class Memento {

    private int money;//玩家的金币

    ArrayList fruits;// 玩家获取的水果

    public Memento(int money) {
        this.money = money;
    }

    public Memento(int money, ArrayList fruits) {
        this.money = money;
        this.fruits = fruits;
    }

    // 获取当前玩家的金币
    public int getMoney() {
        return money;
    }

    // 获取当前玩家的水果
    List getFruits() {
        return (List) fruits.clone();
    }

    // 添加水果的方法
    void addFruit(String fruit){
        fruits.add(fruit);
    }
}
