package com.ac.springboot.design.create.builder.builder01.builder.impl;

import com.ac.springboot.design.create.builder.builder01.builder.Builder;
import com.ac.springboot.design.create.builder.builder01.product.Bike;

/**
 * 摩拜单车建造者
 * @Author: zhangyadong
 * @Date: 2022/11/27 19:47
 */
public class MobikeBuilder extends Builder {

    @Override
    public void builderFrame() {
        System.out.println("自作车架！");
        mbike.setFrame("铝合金车架！");
    }

    @Override
    public void builderSeat() {
        System.out.println("制作车座！");
        mbike.setSeat("真皮车座！");
    }

    @Override
    public Bike createBike() {
        return mbike;
    }
}
