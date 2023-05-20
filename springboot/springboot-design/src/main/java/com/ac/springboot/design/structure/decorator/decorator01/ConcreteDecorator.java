package com.ac.springboot.design.structure.decorator.decorator01;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/14 21:58
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();// 调用业务方法
        // 在原有方法的基础上进行了扩展功能
        add();
    }

    // 新增业务方法
    public void add() {

        //......
    }

}
