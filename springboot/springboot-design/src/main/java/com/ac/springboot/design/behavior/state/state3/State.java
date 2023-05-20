package com.ac.springboot.design.behavior.state.state3;

import com.ac.springboot.design.behavior.state.state3.TrafficLight;

/**
 * 交通灯状态接口
 * @Author: zhangyadong
 * @Date: 2022/12/24 20:59
 */
public interface State {

    void switchToGreen(TrafficLight trafficLight);// 切换为绿灯

    void switchToYellow(TrafficLight trafficLight);// 切换为黄灯

    void switchToRed(TrafficLight trafficLight);// 切换为红灯
}
