package com.ac.springboot.design.create.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/24 17:57
 */
@SpringBootTest
public class Singleton_03tEST {

    @Test
    public void singleton_03Test(){
        for (int i = 0; i < 500; i++) {
            new Thread(() ->{
                Singleton_03 instance = Singleton_03.getInstance();
                System.out.println(instance);
            }).start();
        }
    }
}
