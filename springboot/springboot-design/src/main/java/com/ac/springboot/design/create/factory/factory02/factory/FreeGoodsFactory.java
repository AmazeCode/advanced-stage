package com.ac.springboot.design.create.factory.factory02.factory;


import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;

/**
 * 工厂方法模式-抽象工厂
 * @Author: zhangyadong
 * @Date: 2022/11/26 16:28
 */
public interface FreeGoodsFactory {

    // 返回抽象产品
    IFreeGoods getInstance();
}
