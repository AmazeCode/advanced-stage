package com.ac.springboot.design.behavior.state.state3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 组合模式测试
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:10
 */
@SpringBootTest
public class State3Test {

    @Test
    public void state3Test() {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.switchToRed();
        trafficLight.switchToGreen();
        trafficLight.switchToYellow();
    }
}
