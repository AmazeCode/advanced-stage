package com.ac.springboot.design.create.factory.factory01.controller;

import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory01.factory.FreeGoodsFactory;
import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;

/**
 * 简单工厂(非设计模式)
 * @Author: zhangyadong
 * @Date: 2022/11/25 19:05
 */
public class DeliverController {

    /*
        简单工厂模式优点：
            封装类创建对象的过程，可以通过参数直接获取对象，把对象的创建和业务逻辑分开，
            避免了可能会修改客户端代码的问题
            如果要实现新产品，直接修改工厂类，不需要在源代码中进行修改，降低了客户端修改代码的可能性，更加容易扩展
        简单工厂模式缺点：
            在增加新产品的时候还是要修改工厂类的代码，违背了开闭原则。
     */

    // 发放奖品
    public ResponseResult awardToUser(AwardInfo awardInfo) {
        try {
            // 通过工厂类获取实例
            IFreeGoods instance = FreeGoodsFactory.getInstance(awardInfo.getAwardTypes());
            // 发送奖品
            ResponseResult responseResult = instance.sendFreeGoods(awardInfo);
            return responseResult;
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult("201","奖品发放失败！");
        }

    }
}
