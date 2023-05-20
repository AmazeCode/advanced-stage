package com.ac.springboot.design.behavior.state.state2;

/**
 * 交通等类三种状态
 *  红灯（禁行），黄灯（警示），绿灯（通行）
 * @Author: zhangyadong
 * @Date: 2022/12/24 20:47
 */
public class TrafficLight {

    // 初始化状态
    private String state = "红色";

    // 切换为绿灯，通行状态
    public void switchToGreen() {
        if ("绿".equals(state)) {// 当前是绿灯
            System.out.println("当前为绿灯，无需切换！");
        }else if ("红".equals(state)) {
            System.out.println("红灯不能切换为绿灯");
        }else if("黄".equals(state)) {
            state = "绿";
            System.out.println("绿灯亮起....时长：60秒");
        }
    }

    // 切换为黄灯，警告状态
    public void switchToYellow() {
        if ("黄".equals(state)) {// 当前是黄灯
            System.out.println("当前为黄灯，无需切换！");
        }else if ("红".equals(state) || "绿".equals(state)) {
            state = "黄";
            System.out.println("黄灯亮起....时长：10秒");
        }
    }

    // 切换为红灯，禁止状态
    public void switchToRed() {
        if ("红".equals(state)) {// 当红是绿灯
            System.out.println("当前为红灯，无需切换！");
        }else if ("绿".equals(state)) {
            System.out.println("绿灯不能切换为红灯");
        }else if("黄".equals(state)) {
            state = "红";
            System.out.println("红灯亮起....时长：90秒");
        }
    }
}
