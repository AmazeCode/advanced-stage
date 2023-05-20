package com.ac.springboot.design.create.builder.builder01.builder.impl;

import com.ac.springboot.design.create.builder.builder01.builder.Builder;
import com.ac.springboot.design.create.builder.builder01.product.Bike;

/**
 * 哈罗单车建造者
 * @Author: zhangyadong
 * @Date: 2022/11/27 19:50
 */
public class HaluoBikeBuilder extends Builder {

    @Override
    public void builderFrame() {
        System.out.println("制作车架！");
        mbike.setFrame("碳纤维车架！");
    }

    @Override
    public void builderSeat() {
        System.out.println("制作车座！");
        mbike.setSeat("橡胶车座！");
    }

    @Override
    public Bike createBike() {
        return mbike;
    }
}
