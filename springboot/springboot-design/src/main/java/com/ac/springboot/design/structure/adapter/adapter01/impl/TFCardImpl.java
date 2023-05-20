package com.ac.springboot.design.structure.adapter.adapter01.impl;

import com.ac.springboot.design.structure.adapter.adapter01.TFCard;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:25
 */
public class TFCardImpl implements TFCard {

    @Override
    public String readTF() {
        String msg = "tf card reading data";
        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("tf card write data:" + msg);
    }
}
