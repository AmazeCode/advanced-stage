package com.ac.springboot.design.structure.decorator.decorator02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/14 22:45
 */
@SpringBootTest
public class Decorator02Test {

    @Test
    public void decoratorTest() {

        String info = "name:tom,age:30";
        /*
        // 未加密
        DataLoader loader = new BaseFileDataLoader("dome.txt");
        loader.write(info);

        loader.read();
        */

        DataLoaderDecorator decorator = new EncryptionDataDecorator(new BaseFileDataLoader("demo2.txt"));
        decorator.write(info);

        decorator.read();
    }
}
