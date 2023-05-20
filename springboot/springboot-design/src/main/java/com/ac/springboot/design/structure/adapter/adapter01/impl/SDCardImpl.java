package com.ac.springboot.design.structure.adapter.adapter01.impl;

import com.ac.springboot.design.structure.adapter.adapter01.SDCard;

/**
 * SD卡实现类
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:21
 */
public class SDCardImpl implements SDCard {

    @Override
    public String readSD() {
        String msg = "sd card reading data";
        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("sd card write data:" + msg);
    }
}
