package com.ac.springboot.design.behavior.template.template1;

/**
 * 抽象父类
 * @Author: zhangyadong
 * @Date: 2022/12/23 21:29
 */
public abstract class AbstractClassTemplate {

    void step1(String key) {
        System.out.println("在模板类中 -> 执行步骤1");
        if (step2(key)) {
            step3();
        } else {
            step4();
        }

        step5();
    }

    boolean step2(String key) {
        System.out.println("在模板类中 -> 执行步骤2");
        if ("x".equals(key)) {
            return true;
        }
        return false;
    }

    abstract void step3();

    abstract void step4();

    void step5() {
        System.out.println("在模板类中 -> 执行步骤5");
    }


    /**
     * @description: 模板方法
     * @param: key
     * @return: void
     * @author: zhangyadong
     * @date: 2022/12/23 21:34
     */
    void run(String key) {
        step1(key);
    }
}
