package com.ac.springboot.design.behavior.mediator.mediator2;

/**
 * 抽象同事类
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:38
 */
public class Person {

    protected String name;

    // 持有中介者的引用
    protected Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
