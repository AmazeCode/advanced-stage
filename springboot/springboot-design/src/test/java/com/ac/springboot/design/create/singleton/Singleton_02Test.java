package com.ac.springboot.design.create.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/24 17:42
 */
@SpringBootTest
public class Singleton_02Test {

    @Test
    public void singleton_02Test() {
        for (int i = 0; i < 500; i++) {
            new Thread(() ->{
                Singleton_02 instance = Singleton_02.getInstance();
                // 多次输出会发现对象不一致
                System.out.println(instance);
            }).start();
        }
    }
}
