package com.ac.springboot.design.behavior.observer.observer01;

/**
 * 抽象目标类
 * @Author: zhangyadong
 * @Date: 2022/12/17 14:42
 */
public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();
}
