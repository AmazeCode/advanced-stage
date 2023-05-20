package com.ac.springboot.design.behavior.expression.expression2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 解释器模式测试
 * @Author: zhangyadong
 * @Date: 2022/12/25 14:59
 */
@SpringBootTest
public class Expression2Test {

    @Test
    public void expression2Test() {
        ExpressionInterpreter e = new ExpressionInterpreter();
        long result = e.interpreter("6 2 3 2 4 / - + *");
        System.out.println(result);
    }
}
