package com.ac.springboot.design.behavior.mediator.mediator1;

/**
 * 具体中介者
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:23
 */
public class MediatorImpl implements Mediator {

    @Override
    public void apply(String key) {
        System.out.println("最终中介者执行的操作，key为：" + key);
    }
}
