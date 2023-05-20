package com.ac.springboot.design.behavior.mediator.mediator1;

/**
 * 抽象同事类
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:24
 */
public abstract class Colleague {

    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    // 同事间进行交互的抽象方法
    public abstract void exec(String key);
}
