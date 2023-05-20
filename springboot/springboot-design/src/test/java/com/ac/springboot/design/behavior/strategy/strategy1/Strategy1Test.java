package com.ac.springboot.design.behavior.strategy.strategy1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/23 22:43
 */
@SpringBootTest
public class Strategy1Test {

    @Test
    public void strategy1Test() {
        Strategy strategyA = new ConcreteStrategyA();
        Context contextA = new Context(strategyA);
        contextA.algorithm();

        System.out.println("==========================");

        Strategy strategyB = new ConcreteStrategyB();
        Context contextB = new Context(strategyB);
        contextB.algorithm();
    }
}
