package com.ac.springboot.design.create.factory.factory01.service.impl;

import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;

/**
 * 优酷会员服务
 * @Author: zhangyadong
 * @Date: 2022/11/25 18:35
 */
public class YouKuMemberService implements IFreeGoods {
    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        String phone = awardInfo.getExtMap().get("phone");
        System.out.println("发放优酷会员成功，绑定手机号：" + phone);
        return new ResponseResult("200","优酷会员发放成功！");
    }
}
