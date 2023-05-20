package com.ac.springboot.design.structure.decorator.decorator01;

/**
 * 抽象构建类
 * @Author: zhangyadong
 * @Date: 2022/12/14 21:48
 */
public abstract class Component {

    /*
        整体流程：
        1、定义抽象构件类Component
        2、定义具体构件类ConcreteComponent(被装饰类) extend Component
        3、定义抽象装饰类Decorator extend Component，维持具体构件数属性（Component）,直接返回被装饰方法，不做特殊处理
        4、定义具体装饰类ConcreteDecorator extend Decorator,新增方法，在原有方法内调用，实现方法功能的扩展
     */

    // 抽象方法
    public abstract void operation();
}
