package com.ac.springboot.design.create.builder.builder01.builder;

import com.ac.springboot.design.create.builder.builder01.product.Bike;

/**
 * 抽象建造者
 * @Author: zhangyadong
 * @Date: 2022/11/27 19:40
 */
public abstract class Builder {

    // 让子类使用
    protected Bike mbike = new Bike();

    /**
     * @description: 建造车架
     * @param:
     * @return: void
     * @author: zhangyadong
     * @date: 2022/11/27 19:42
     */
    public abstract void builderFrame();

    /**
     * @description: 建造车座
     * @param:
     * @return: void
     * @author: zhangyadong
     * @date: 2022/11/27 19:43
     */
    public abstract void builderSeat();

    /**
     * @description: 创建自行车
     * @param:
     * @return: com.ac.springboot.design.create.builder.builder01.product.Bike
     * @author: zhangyadong
     * @date: 2022/11/27 19:44
     */
    public abstract Bike createBike();
}
