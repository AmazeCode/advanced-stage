package com.ac.springboot.design.behavior.observer.observer01;

/**
 * 抽象的观察者
 * @Author: zhangyadong
 * @Date: 2022/12/17 14:38
 */
public interface Observer {

    // update方法：为不同的观察者更新行为定义一个相同的接口，不同的观察者对该接口有不同的实现
    public void update();
}
