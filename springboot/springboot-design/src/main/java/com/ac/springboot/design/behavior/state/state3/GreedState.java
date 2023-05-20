package com.ac.springboot.design.behavior.state.state3;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:03
 */
public class GreedState implements State {

    @Override
    public void switchToGreen(TrafficLight trafficLight) {
        System.out.println("当前是绿灯，无需切换");
    }

    @Override
    public void switchToYellow(TrafficLight trafficLight) {
        System.out.println("黄灯亮起...时长：10秒");
    }

    @Override
    public void switchToRed(TrafficLight trafficLight) {
        System.out.println("绿灯不能够切换为红灯！");
    }
}
