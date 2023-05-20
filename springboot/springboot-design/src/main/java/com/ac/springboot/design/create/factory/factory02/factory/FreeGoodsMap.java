package com.ac.springboot.design.create.factory.factory02.factory;

import com.ac.springboot.design.create.factory.factory02.factory.impl.DiscountFreeGoodsFactory;
import com.ac.springboot.design.create.factory.factory02.factory.impl.SmallGiftGoodsFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂的工厂，用来创建工厂类的对象
 * @Author: zhangyadong
 * @Date: 2022/11/26 16:54
 */
public class FreeGoodsMap {

    // 创建map集合，保存工厂的对象
    private static final Map<Integer,FreeGoodsFactory> cachedFactorires = new HashMap<>();

    static{
        // 保存具体工厂对象
        cachedFactorires.put(1,new DiscountFreeGoodsFactory());
        cachedFactorires.put(3,new SmallGiftGoodsFactory());
    }

    /**
     * @description: 提供对外访问方法
     * @param: type
     * @return: com.ac.springboot.design.create.factory.factory02.factory.FreeGoodsFactory
     * @author: zhangyadong
     * @date: 2022/11/26 17:03
     */
    public static FreeGoodsFactory getParseFactory(Integer type) {
        FreeGoodsFactory freeGoodsFactory = cachedFactorires.get(type);
        if (freeGoodsFactory != null) {
            return freeGoodsFactory;
        }
        return null;
    }
}
