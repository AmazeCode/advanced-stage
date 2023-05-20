package com.ac.springboot.design.create.factory.factory02.factory.impl;

import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;
import com.ac.springboot.design.create.factory.factory01.service.impl.SmallGiftFreeGoods;
import com.ac.springboot.design.create.factory.factory02.factory.FreeGoodsFactory;

/**
 * 生产发放小礼品接口-具体工厂
 * @Author: zhangyadong
 * @Date: 2022/11/26 16:36
 */
public class SmallGiftGoodsFactory implements FreeGoodsFactory {

    @Override
    public IFreeGoods getInstance() {
        // 返回具体的产品
        return new SmallGiftFreeGoods();
    }
}
