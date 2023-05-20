package com.ac.springboot.design.create.factory.factory01.factory;

import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;
import com.ac.springboot.design.create.factory.factory01.service.impl.DiscountFreeGoods;
import com.ac.springboot.design.create.factory.factory01.service.impl.SmallGiftFreeGoods;
import com.ac.springboot.design.create.factory.factory01.service.impl.YouKuMemberService;

/**
 * 具体工厂：生成免费商品
 * @Author: zhangyadong
 * @Date: 2022/11/25 18:42
 */
public class FreeGoodsFactory {

    public static IFreeGoods getInstance(Integer awardType) {
        IFreeGoods iFreeGoods = null;
        if (awardType == 1) {// 打折券
            iFreeGoods = new DiscountFreeGoods();
        } else if (awardType == 2) {// 优酷会员
            iFreeGoods = new YouKuMemberService();
        } else if (awardType == 3) { // 小礼品
            iFreeGoods = new SmallGiftFreeGoods();
        }
        return iFreeGoods;
    }
}
