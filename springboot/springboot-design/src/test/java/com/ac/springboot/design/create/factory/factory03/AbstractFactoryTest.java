package com.ac.springboot.design.create.factory.factory03;

import com.ac.springboot.design.create.factory.factory03.factory.HairFactory;
import com.ac.springboot.design.create.factory.factory03.product.AbstractFreezer;
import com.ac.springboot.design.create.factory.factory03.product.AbstractTV;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 抽象工厂测试
 * @Author: zhangyadong
 * @Date: 2022/11/26 21:10
 */
@SpringBootTest
public class AbstractFactoryTest {

    @Test
    public void abstractFactoryTest() {
        AbstractFactoryClient abstractFactoryClient = new AbstractFactoryClient(new HairFactory());
        // 获取电视（海尔）
        AbstractTV tv = abstractFactoryClient.getTv();
        System.out.println(tv);
        // 获取冰箱（海尔）
        AbstractFreezer freezer = abstractFactoryClient.getFreezer();
        System.out.println(freezer);
    }
}
