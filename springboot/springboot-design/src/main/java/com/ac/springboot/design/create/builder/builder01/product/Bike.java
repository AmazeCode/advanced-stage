package com.ac.springboot.design.create.builder.builder01.product;

/**
 * 自行车
 * @Author: zhangyadong
 * @Date: 2022/11/27 19:38
 */
public class Bike {

    private String frame; // 车架

    private String seat; // 车座

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
