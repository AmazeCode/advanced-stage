package com.ac.springboot.design.create.factory.factory02.factory.impl;

import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;
import com.ac.springboot.design.create.factory.factory01.service.impl.DiscountFreeGoods;
import com.ac.springboot.design.create.factory.factory02.factory.FreeGoodsFactory;

/**
 * 生产优惠券发放接口-具体工厂
 * @Author: zhangyadong
 * @Date: 2022/11/26 16:32
 */
public class DiscountFreeGoodsFactory implements FreeGoodsFactory {

    @Override
    public IFreeGoods getInstance() {
        // 返回具体产品
        return new DiscountFreeGoods();
    }
}
