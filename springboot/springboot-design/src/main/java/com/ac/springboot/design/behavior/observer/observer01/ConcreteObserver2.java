package com.ac.springboot.design.behavior.observer.observer01;

/**
 * 具体观察者2
 * @Author: zhangyadong
 * @Date: 2022/12/17 14:41
 */
public class ConcreteObserver2  implements Observer {

    @Override
    public void update() {
        System.out.println("ConcreteObserver2 得到通知，更新状态！！");
    }
}
