package com.ac.springboot.design.structure.flyweight.flyweight02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 21:58
 */
@SpringBootTest
public class FlyweightTest02 {

    @Test
    public void flyweightTest02() {
        GobangFactory instance = GobangFactory.getInstance();

        // 获取3颗黑子
        GobangFlyweight b1 = instance.getGobang("b");
        GobangFlyweight b2 = instance.getGobang("b");
        GobangFlyweight b3 = instance.getGobang("b");
        System.out.println("判断黑子是否时同一对象：" + (b1 == b2));

        GobangFlyweight w1 = instance.getGobang("w");
        GobangFlyweight w2 = instance.getGobang("w");
        System.out.println("判断白子是否时同一对象：" + (w1 == w2));

        // 显示棋子
        b1.display();
        b2.display();
        b3.display();

        w1.display();
        w2.display();

    }
}
