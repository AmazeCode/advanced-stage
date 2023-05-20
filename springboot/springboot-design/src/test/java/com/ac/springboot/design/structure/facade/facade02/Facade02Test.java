package com.ac.springboot.design.structure.facade.facade02;

import com.ac.springboot.design.structure.facade.facade01.Facade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 15:53
 */
@SpringBootTest
public class Facade02Test {

    @Test
    public void facade02Test() {
        SmartAppliancesFacade facade = new SmartAppliancesFacade();
        facade.say("打开");

        facade.say("关闭");
    }
}
