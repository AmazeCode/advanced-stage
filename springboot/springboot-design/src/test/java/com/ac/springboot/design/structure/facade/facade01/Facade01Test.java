package com.ac.springboot.design.structure.facade.facade01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 14:22
 */
@SpringBootTest
public class Facade01Test {

    @Test
    public void facade01Test() {
        Facade facade = new Facade();
        facade.method();
    }
}
