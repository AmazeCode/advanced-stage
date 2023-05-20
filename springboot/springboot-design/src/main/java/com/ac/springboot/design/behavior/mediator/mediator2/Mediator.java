package com.ac.springboot.design.behavior.mediator.mediator2;

/**
 * 抽象中介者
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:36
 */
public abstract class Mediator {

    public abstract void contact(String message, Person person);
}
