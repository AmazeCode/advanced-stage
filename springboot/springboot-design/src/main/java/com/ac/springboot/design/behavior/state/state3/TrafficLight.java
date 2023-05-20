package com.ac.springboot.design.behavior.state.state3;

/**
 * 交通灯类
 * @Author: zhangyadong
 * @Date: 2022/12/24 20:59
 */
public class TrafficLight {

    // 初始化-红灯
    State state = new RedState();

    public void setState(State state) {
        this.state = state;
    }

    // 切换为绿灯，通行状态
    public void switchToGreen() {
        state.switchToGreen(this);
    }

    // 切换为黄灯，警告状态
    public void switchToYellow() {
        state.switchToYellow(this);
    }

    // 切换为红灯，禁止状态
    public void switchToRed() {
        state.switchToRed(this);
    }
}
