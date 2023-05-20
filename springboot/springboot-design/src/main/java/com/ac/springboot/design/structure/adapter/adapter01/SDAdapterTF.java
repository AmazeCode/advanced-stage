package com.ac.springboot.design.structure.adapter.adapter01;

import com.ac.springboot.design.structure.adapter.adapter01.impl.TFCardImpl;

/**
 * 适配器类(SD兼容TF)
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:33
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }
}
