package com.ac.springboot.design.behavior.strategy.strategy1;

/**
 * 具体策略B
 * @Author: zhangyadong
 * @Date: 2022/12/23 22:39
 */
public class ConcreteStrategyB implements Strategy {

    @Override
    public void algorithm() {
        System.out.println("执行策略B");
    }
}
