package com.ac.springboot.design.behavior.command.command1;

/**
 * 厨师类-》Receiver接收者角色
 * @Author: zhangyadong
 * @Date: 2022/12/25 12:37
 */
public class Chef {

    public void makeFood(int num, String foodName) {
        System.out.println(num + "份" + foodName);
    }
}
