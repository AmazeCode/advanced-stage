package com.ac.springboot.design.create.factory.factory03.factory;

import com.ac.springboot.design.create.factory.factory03.product.AbstractFreezer;
import com.ac.springboot.design.create.factory.factory03.product.AbstractTV;
import com.ac.springboot.design.create.factory.factory03.product.HisenseFreezer;
import com.ac.springboot.design.create.factory.factory03.product.HisenseTV;

/**
 * 具体工厂（海信-工厂）
 * @Author: zhangyadong
 * @Date: 2022/11/26 21:03
 */
public class HisenseFactory implements ApplicancesFactory{

    @Override
    public AbstractTV createTV() {
        return new HisenseTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HisenseFreezer();
    }
}
