package com.ac.springboot.design.structure.flyweight.flyweight01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:37
 */
@SpringBootTest
public class FlyweightTest01 {

    @Test
    public void flyweightTest01() {
       // 获取工厂对象
        FlyweightFactory factory = new FlyweightFactory();

        // 通过工厂获取共享的享元对象
        Flyweight a1 = factory.getFlyweight("A");
        a1.operation("a1ExState");

        Flyweight a2 = factory.getFlyweight("A");
        a2.operation("a2ExState");

        System.out.println(a1 == a2);

        // 获取非共享的享元对象
        UnsharedFlyweight u1 = new UnsharedFlyweight("A");
        UnsharedFlyweight u2 = new UnsharedFlyweight("A");

        System.out.println(u1 == u2);

    }
}
