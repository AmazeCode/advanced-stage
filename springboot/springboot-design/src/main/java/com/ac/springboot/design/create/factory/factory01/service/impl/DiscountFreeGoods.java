package com.ac.springboot.design.create.factory.factory01.service.impl;

import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;

/**
 * 模拟打折券接口
 * @Author: zhangyadong
 * @Date: 2022/11/25 18:32
 */
public class DiscountFreeGoods implements IFreeGoods {

    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        System.out.println("向用户发放一张打折券" + awardInfo.getUid() + "," + awardInfo.getAwardNumber());
        return new ResponseResult("200","打折券发送成功！");
    }
}
