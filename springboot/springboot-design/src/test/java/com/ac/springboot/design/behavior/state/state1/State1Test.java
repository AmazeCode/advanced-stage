package com.ac.springboot.design.behavior.state.state1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 状态模式测试
 * @Author: zhangyadong
 * @Date: 2022/12/24 20:38
 */
@SpringBootTest
public class State1Test {

    @Test
    public void state1Test() {
        Context context = new Context();
        State state1 = new ConcreteStateA();
        state1.handle(context);
        System.out.println(context.getCurrentState().toString());

        Context context1 = new Context();
        State state2 = new ConcreteStateB();
        state2.handle(context1);
        System.out.println(context1.getCurrentState().toString());
    }
}
