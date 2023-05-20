package com.ac.springboot.design.structure.decorator.decorator01;

/**
 * 具体构建类(被装饰类)
 * @Author: zhangyadong
 * @Date: 2022/12/14 21:50
 */
public class ConcreteComponent extends Component{

    @Override
    public void operation() {
        // 基础功能的实现（一些复杂功能通过装饰类进行扩展）
    }
}
