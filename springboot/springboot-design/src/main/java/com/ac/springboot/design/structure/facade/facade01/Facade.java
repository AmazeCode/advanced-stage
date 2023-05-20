package com.ac.springboot.design.structure.facade.facade01;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 14:20
 */
public class Facade {

    private SubSystemA obj1 = new SubSystemA();
    private SubSystemB obj2 = new SubSystemB();
    private SubSystemC obj3 = new SubSystemC();

    public void method() {
        obj1.methodA();
        obj2.methodB();
        obj3.methodC();
    }

}
