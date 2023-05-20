package com.ac.springboot.design.behavior.mediator.mediator2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 中介者模式测试
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:50
 */
@SpringBootTest
public class Mediator2Test {

    @Test
    public void mediator2Test() {
        // 中介机构
        MediatorStructure mediator = new MediatorStructure();

        // 房主
        HouseOwner houseOwner = new HouseOwner("张三", mediator);

        // 租房者
        Tenant tenant = new Tenant("李四", mediator);

        // 中介收集房主及租房者信息
        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        // 租房人需求
        tenant.contact("需要在天通苑附近找一个，两室一厅的房子一家人住，房租在5000~6000之间");

        // 房主需求
        houseOwner.contact("出租一套天通苑地铁站附近的两室一厅，房租6000，可谈");
    }
}
