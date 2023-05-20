package com.ac.springboot.design.create.factory.factory01.service.impl;

import cn.hutool.json.JSONUtil;
import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;
import com.ac.springboot.design.create.factory.lower.entity.SmallGiftInfo;

import java.util.UUID;

/**
 * 发放小礼品服务
 * @Author: zhangyadong
 * @Date: 2022/11/25 18:38
 */
public class SmallGiftFreeGoods implements IFreeGoods {
    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        // 封装收获人信息
        SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
        smallGiftInfo.setUserPhone(awardInfo.getExtMap().get("phone"));
        smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
        smallGiftInfo.setAddress(awardInfo.getExtMap().get("address"));
        smallGiftInfo.setOrderId(UUID.randomUUID().toString());

        System.out.println("小礼品发放成功，请注意查收！" + JSONUtil.toJsonStr(smallGiftInfo));
        return new ResponseResult("200","小礼品发放成功！",smallGiftInfo);
    }
}
