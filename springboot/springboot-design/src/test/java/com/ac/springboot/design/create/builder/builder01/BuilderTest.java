package com.ac.springboot.design.create.builder.builder01;

import com.ac.springboot.design.create.builder.builder01.builder.impl.MobikeBuilder;
import com.ac.springboot.design.create.builder.builder01.product.Bike;
import com.ac.springboot.design.create.builder.builder01.director.Director;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 建造者模式测试
 * @Author: zhangyadong
 * @Date: 2022/11/27 20:04
 */
@SpringBootTest
public class BuilderTest {

    @Test
    public void builderTest() {
        // 1、创建指挥者
        Director director = new Director(new MobikeBuilder());

        // 2、获取自行车
        Bike bike = director.construct();
        System.out.println(bike.getFrame() + "====" + bike.getSeat());
    }
}
