package com.ac.springboot.design.behavior.mediator.mediator1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:30
 */
@SpringBootTest
public class Mediator1Test {

    @Test
    public void mediator1Test() {
        // 创建中介者
        Mediator mediator = new MediatorImpl();

        // 创建同事对象
        Colleague c1 = new ConcreteColleagueA(mediator);
        c1.exec("key-A");

        Colleague c2 = new ConcreteColleagueB(mediator);
        c2.exec("key-B");

    }
}
