package com.ac.springboot.design.create.factory.factory03.factory;

import com.ac.springboot.design.create.factory.factory03.product.AbstractFreezer;
import com.ac.springboot.design.create.factory.factory03.product.AbstractTV;
import com.ac.springboot.design.create.factory.factory03.product.HairFreezer;
import com.ac.springboot.design.create.factory.factory03.product.HairTV;

/**
 * 具体工厂（海尔-工厂）
 * @Author: zhangyadong
 * @Date: 2022/11/26 21:00
 */
public class HairFactory implements ApplicancesFactory{

    @Override
    public AbstractTV createTV() {
        return new HairTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HairFreezer();
    }
}
