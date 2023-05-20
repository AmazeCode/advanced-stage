package com.ac.springboot.design.create.factory.factory02.controller;

import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;
import com.ac.springboot.design.create.factory.factory02.factory.FreeGoodsMap;
import com.ac.springboot.design.create.factory.factory02.factory.impl.DiscountFreeGoodsFactory;
import com.ac.springboot.design.create.factory.factory02.factory.FreeGoodsFactory;
import com.ac.springboot.design.create.factory.factory02.factory.impl.SmallGiftGoodsFactory;

/**
 * 发放奖品接口
 * @Author: zhangyadong
 * @Date: 2022/11/26 16:39
 */
public class DeliverController {

    /*
        工厂方法模式的优点：
            用户只需要知道具体工厂的名字，就可以获取到想要的产品，不需要关注产品的创建过程
            在系统新增加产品的时候，只需要添加具体产品类和对应的具体工厂，不需要对原工厂进行修改
            满足开闭原则
         工厂方法缺点：
            每增加一个产品，就需要一个具体产品类和对应的具体工厂类，这样就会增加系统的复杂程度
     */

    // 发放奖品
    public ResponseResult awardToUser(AwardInfo awardInfo) {

        /**
         * 存在的问题,还是需要使用if else进行判断具体使用哪种工厂，违反开闭原则
         */
        // 抽象工厂
        FreeGoodsFactory freeGoodsFactory = null;
        if (awardInfo.getAwardTypes() == 1) {
            freeGoodsFactory = new DiscountFreeGoodsFactory();
        } else if (awardInfo.getAwardTypes() == 3) {
            freeGoodsFactory = new SmallGiftGoodsFactory();
        }

        // 抽象产品
        IFreeGoods instance = freeGoodsFactory.getInstance();
        System.out.println("============ 工厂方法模式 ================");
        // 发放礼品
        ResponseResult responseResult = instance.sendFreeGoods(awardInfo);
        return responseResult;
    }

    // 发放奖品优化版
    public ResponseResult awardToUser2(AwardInfo awardInfo) {

        /**
         * 解决使用if else进行判断具体使用哪种工厂问题，满足开闭原则
         */
        // 根据类型获取具体工厂指向抽象工厂
        FreeGoodsFactory parseFactory = FreeGoodsMap.getParseFactory(awardInfo.getAwardTypes());
        if (parseFactory == null) {
            return new ResponseResult("500","获取具体工厂异常！");
        }
        // 从工厂类中获取对应的实例
        IFreeGoods instance = parseFactory.getInstance();
        System.out.println("============ 工厂方法模式 ================");
        // 发放礼品
        ResponseResult responseResult = instance.sendFreeGoods(awardInfo);
        return responseResult;
    }
}
