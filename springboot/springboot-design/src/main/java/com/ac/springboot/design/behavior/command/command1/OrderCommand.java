package com.ac.springboot.design.behavior.command.command1;

import java.util.Set;

/**
 * 具体命令
 * @Author: zhangyadong
 * @Date: 2022/12/25 12:39
 */
public class OrderCommand implements Command {

    // 接收者对象引用
    private Chef receiver;
    private Order order;

    public OrderCommand(Chef receiver, Order order) {
        this.receiver = receiver;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println(order.getDiningTable() + "卓的订单");
        Set<String> keySet = order.getFoodMenu().keySet();
        for (String key : keySet) {
            receiver.makeFood(order.getFoodMenu().get(key),key);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(order.getDiningTable() + "桌的菜品已经上齐");
    }


}
