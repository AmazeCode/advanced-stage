package com.ac.springboot.design.create.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/24 17:30
 */
@SpringBootTest
public class Singleton_O1Test {

    @Test
    public void singleton_01Test(){
        Singleton_01 instance01 = Singleton_01.getInstance();
        Singleton_01 instance02 = Singleton_01.getInstance();
        System.out.println(instance01 +"====" +instance02);
    }
}
