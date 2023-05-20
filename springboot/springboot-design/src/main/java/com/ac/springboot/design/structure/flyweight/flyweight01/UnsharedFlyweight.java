package com.ac.springboot.design.structure.flyweight.flyweight01;

/**
 * 非共享的具体享元类
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:23
 */
public class UnsharedFlyweight extends Flyweight{

    private String inState;

    public UnsharedFlyweight(String inState) {
        this.inState = inState;
    }
    @Override
    public void operation(String state) {
        System.out.println("==== 使用不同享对象,内部状态："+ inState + ",外部状态：" + state);
    }
}
