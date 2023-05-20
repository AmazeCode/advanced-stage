package com.ac.springboot.design.behavior.expression.expression1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 解释器模式测试
 * @Author: zhangyadong
 * @Date: 2022/12/25 14:35
 */
@SpringBootTest
public class Expression1Test {

    @Test
    public void expression1Test() {
        ExpressionInterpreter interpreter = new ExpressionInterpreter();
        long result = interpreter.interpret("9 5 7 3 - + *");
        System.out.println("9 5 7 3 - + * 表达式的结果为："+result);
    }
}
