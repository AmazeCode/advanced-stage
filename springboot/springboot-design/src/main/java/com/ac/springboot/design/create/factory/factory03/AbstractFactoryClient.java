package com.ac.springboot.design.create.factory.factory03;

import com.ac.springboot.design.create.factory.factory03.factory.ApplicancesFactory;
import com.ac.springboot.design.create.factory.factory03.product.AbstractFreezer;
import com.ac.springboot.design.create.factory.factory03.product.AbstractTV;

/**
 * 客户端
 * @Author: zhangyadong
 * @Date: 2022/11/26 21:05
 */
public class AbstractFactoryClient {

    /*
        抽象电视工厂
     */
    private AbstractTV tv;

    /*
        抽象冰箱工厂
     */
    private AbstractFreezer freezer;

    public AbstractFactoryClient(ApplicancesFactory factory) {
        // 在客户端看来就是使用抽象工厂来生产家电
        this.tv = factory.createTV();
        this.freezer = factory.createFreezer();
    }

    public AbstractTV getTv() {
        return tv;
    }

    public void setTv(AbstractTV tv) {
        this.tv = tv;
    }

    public AbstractFreezer getFreezer() {
        return freezer;
    }

    public void setFreezer(AbstractFreezer freezer) {
        this.freezer = freezer;
    }
}
