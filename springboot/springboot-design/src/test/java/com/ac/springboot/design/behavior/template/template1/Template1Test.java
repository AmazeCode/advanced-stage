package com.ac.springboot.design.behavior.template.template1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/23 21:38
 */
@SpringBootTest
public class Template1Test {

    @Test
    public void template1Test() {
        AbstractClassTemplate abstractClassTemplate = new ConcreteClassA();
        abstractClassTemplate.run("x");
        System.out.println("================================");

        AbstractClassTemplate abstractClassTemplateB = new ConcreteClassB();
        abstractClassTemplateB.run("");
    }
}
