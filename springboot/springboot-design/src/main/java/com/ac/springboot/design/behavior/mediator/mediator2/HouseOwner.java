package com.ac.springboot.design.behavior.mediator.mediator2;

/**
 * 具体同事类-房屋拥有者（房主）
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:40
 */
public class HouseOwner extends Person {

    public HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 与中介联系
    public void contact(String message) {
        mediator.contact(message,this);
    }

    // 获取信息的方法
    public void getMessage(String message) {
        System.out.println("房主：" + name + ",获取到的信息" + message);
    }
}
