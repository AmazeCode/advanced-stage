package com.ac.springboot.design.create.factory.lower.service;

import cn.hutool.json.JSONUtil;
import com.ac.springboot.design.create.factory.lower.entity.SmallGiftInfo;

/**
 * 礼品服务
 * @Author: zhangyadong
 * @Date: 2022/11/25 15:53
 */
public class SmallGiftService {

    public Boolean giveSmallGift(SmallGiftInfo smallGiftInfo) {
        System.out.println("小礼品已发送，获奖用户注意查收！" + JSONUtil.toJsonStr(smallGiftInfo));
        return true;
    }
}
