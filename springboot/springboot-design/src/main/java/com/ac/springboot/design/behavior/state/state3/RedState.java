package com.ac.springboot.design.behavior.state.state3;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:03
 */
public class RedState implements State {

    @Override
    public void switchToGreen(TrafficLight trafficLight) {
        System.out.println("红灯不能切换为绿灯");
    }

    @Override
    public void switchToYellow(TrafficLight trafficLight) {
        System.out.println("黄灯亮起...时长：10秒");
    }

    @Override
    public void switchToRed(TrafficLight trafficLight) {
        System.out.println("当前为红灯，无需切换");
    }
}
