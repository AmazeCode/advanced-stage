package com.ac.springboot.design.behavior.command.command1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 命令模式测试
 * @Author: zhangyadong
 * @Date: 2022/12/25 12:47
 */
@SpringBootTest
public class Command1Test {

    @Test
    public void command1Test() {

        Order order1 = new Order();
        order1.setDiningTable(10);
        order1.getFoodMenu().put("鲍鱼炒饭",1);
        order1.getFoodMenu().put("海参炒面",1);

        Order order2 = new Order();
        order2.setDiningTable(15);
        order2.getFoodMenu().put("回锅肉盖饭",1);
        order2.getFoodMenu().put("木须肉盖饭",1);

        // 创建接收者
        Chef chef = new Chef();

        // 将订单和接收者封装成命令对象
        OrderCommand orderCommand1 = new OrderCommand(chef, order1);
        OrderCommand orderCommand2 = new OrderCommand(chef, order2);

        // 创建调用者
        Waiter waiter = new Waiter();
        waiter.setCommands(orderCommand1);
        waiter.setCommands(orderCommand2);

        // 将订单发送给厨师 上菜
        waiter.orderUp();

    }
}
