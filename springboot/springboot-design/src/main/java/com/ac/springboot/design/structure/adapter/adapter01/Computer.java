package com.ac.springboot.design.structure.adapter.adapter01;

/**
 * 电脑类
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:28
 */
public class Computer {

    public String read(SDCard sdCard) {
        String data = sdCard.readSD();
        return data;
    }
}
