package com.ac.springboot.design.structure.adapter.adapter02;

import com.ac.springboot.design.structure.adapter.adapter01.SDCard;
import com.ac.springboot.design.structure.adapter.adapter01.TFCard;

/**
 * 对象适配器-组合形式
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:43
 */
public class SDAdapterTF02 implements SDCard {

    private TFCard tfCard;

    public SDAdapterTF02(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return tfCard.readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        tfCard.writeTF(msg);
    }
}
