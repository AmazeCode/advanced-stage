package com.ac.springboot.design.create.builder.builder01.director;

import com.ac.springboot.design.create.builder.builder01.builder.Builder;
import com.ac.springboot.design.create.builder.builder01.product.Bike;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/27 19:53
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 自行车制作方法
     */
    public Bike construct() {
        builder.builderFrame();
        builder.builderSeat();
        return builder.createBike();
    }
}
