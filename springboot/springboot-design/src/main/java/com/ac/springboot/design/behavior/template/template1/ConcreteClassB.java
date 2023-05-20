package com.ac.springboot.design.behavior.template.template1;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/23 21:37
 */
public class ConcreteClassB extends AbstractClassTemplate {

    @Override
    void step3() {
        System.out.println("在子类B中 -> 执行步骤3");
    }

    @Override
    void step4() {
        System.out.println("在子类B中 -> 执行步骤4");
    }
}
