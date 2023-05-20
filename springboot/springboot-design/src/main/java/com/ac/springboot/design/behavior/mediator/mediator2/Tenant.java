package com.ac.springboot.design.behavior.mediator.mediator2;

/**
 * 具体同事类-承租人
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:44
 */
public class Tenant extends Person {

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void contact(String message) {
        mediator.contact(message,this);
    }

    // 获取信息的方法
    public void getMessage(String message) {
        System.out.println("租房者：" + name + ",获取到的信息" + message);
    }
}
