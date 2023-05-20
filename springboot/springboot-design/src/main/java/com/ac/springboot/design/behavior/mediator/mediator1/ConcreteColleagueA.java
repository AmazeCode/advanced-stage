package com.ac.springboot.design.behavior.mediator.mediator1;

/**
 * 具体同事类
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:26
 */
public class ConcreteColleagueA extends Colleague {

    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void exec(String key) {
        System.out.println("====在A同事中,通过中介者执行！");

        getMediator().apply(key);
    }
}
