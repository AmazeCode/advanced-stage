package com.ac.springboot.design.behavior.strategy.strategy1;

/**
 * 具体策略类
 * @Author: zhangyadong
 * @Date: 2022/12/23 22:38
 */
public class ConcreteStrategyA implements Strategy{

    @Override
    public void algorithm() {
        System.out.println("执行策略A");
    }
}
