package com.ac.springboot.design.behavior.observer.observer01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/17 14:52
 */
@SpringBootTest
public class Observer01Test {

    @Test
    public void observer01Test() {
        // 创建目标类
        Subject subject = new ConcreteSubject();

        // 注册观察者，注册多个
        subject.attach(new ConcreteObserver1());
        subject.attach(new ConcreteObserver2());

        // 具体的主题内部发生改变，给所有注册的观察者发送通知
        subject.notifyObservers();
    }
}
