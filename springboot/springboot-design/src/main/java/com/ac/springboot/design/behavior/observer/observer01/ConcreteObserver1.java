package com.ac.springboot.design.behavior.observer.observer01;

/**
 * 具体的观察者1
 * @Author: zhangyadong
 * @Date: 2022/12/17 14:40
 */
public class ConcreteObserver1 implements Observer{

    @Override
    public void update() {
        System.out.println("ConcreteObserver1 得到通知，更新状态！！");
    }
}
