package com.ac.springboot.design.behavior.visit.visit1;

/**
 * 访问者接口-根据入参的不同调用对应的重载方法
 * @Author: zhangyadong
 * @Date: 2022/12/25 10:05
 */
public interface Visit {

    void visit(Candy candy); // 糖果重载方法

    void visit(Wine wine); // 酒类重载方法

    void visit(Fruit fruit); // 水果重载方法
}
