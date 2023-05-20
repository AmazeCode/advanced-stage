package com.ac.springboot.design.behavior.command.command1;

import java.util.ArrayList;

/**
 * 服务员 -> Invoker 调用者
 * @Author: zhangyadong
 * @Date: 2022/12/25 12:44
 */
public class Waiter {

    // 可以持有多个命令对象
    private ArrayList<Command> commands;

    public Waiter() {
        this.commands = new ArrayList<>();
    }

    public Waiter(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public void setCommands(Command command) {
        this.commands.add(command);
    }

    // 发出指令
    public void orderUp() {
        System.out.println("叮咚！服务员:有新的订单，请师傅开始制作....");
        for (Command command : commands) {
            if (command != null) {
                command.execute();
            }
        }
    }
}
