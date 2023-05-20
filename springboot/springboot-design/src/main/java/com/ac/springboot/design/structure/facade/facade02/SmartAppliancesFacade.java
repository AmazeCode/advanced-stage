package com.ac.springboot.design.structure.facade.facade02;

/**
 * 外观模式
 * 智能控制（门面类）
 * @Author: zhangyadong
 * @Date: 2022/12/15 15:31
 */
public class SmartAppliancesFacade {

    private Light light;

    private TV tv;

    private AirCondition airCondition;

    public SmartAppliancesFacade() {
        this.light = new Light();
        this.tv = new TV();
        this.airCondition = new AirCondition();
    }

    public void say (String message) {
        if (message.contains("打开")) {
            on();
        }else if (message.contains("关闭")) {
            off();
        }else {
            System.out.println("没听清您在说什么，请重新说一遍！");
        }
    }

    public void on() {
        light.on();;
        tv.on();
        airCondition.on();
    }

    public void off() {
        light.off();
        tv.off();
        airCondition.off();
    }
}
