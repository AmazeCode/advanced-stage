package com.ac.springboot.design.create.factory.lower.controller;

import com.ac.springboot.design.create.factory.lower.entity.AwardInfo;
import com.ac.springboot.design.create.factory.lower.entity.DiscountResult;
import com.ac.springboot.design.create.factory.lower.entity.SmallGiftInfo;
import com.ac.springboot.design.create.factory.lower.service.DiscountService;
import com.ac.springboot.design.create.factory.lower.service.SmallGiftService;
import com.ac.springboot.design.create.factory.lower.service.YouKuMemberService;

import java.util.UUID;

/**
 * 发放奖品接口
 * @Author: zhangyadong
 * @Date: 2022/11/25 16:04
 */
public class DeliverController {

    /**
     * @description: 按照类型发放奖品 奖品类型：1 打折券，2 优酷会员， 3 小礼品
     * @param: awardInfo
     * @return: void
     * @author: zhangyadong
     * @date: 2022/11/25 16:05
     */
    public void awardToUser(AwardInfo awardInfo) {
        if (awardInfo.getAwardTypes() == 1) {
            // 打折券
            DiscountService discountService = new DiscountService();
            DiscountResult discountResult = discountService.sendDiscount(awardInfo.getUid(),awardInfo.getAwardNumber());
            System.out.println("打折券发放成功!" + discountResult);
        }else if (awardInfo.getAwardTypes() == 2) {
            // 优酷会员
            String phone = awardInfo.getExtMap().get("phone");
            YouKuMemberService youKuMemberService = new YouKuMemberService();
            youKuMemberService.openMemeber(phone,awardInfo.getAwardNumber());
            System.out.println("优酷会员发放成功！");
        }else if (awardInfo.getAwardTypes() == 3) {
            //小礼品
            // 封装收获人信息
            SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
            smallGiftInfo.setUserPhone(awardInfo.getExtMap().get("phone"));
            smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
            smallGiftInfo.setAddress(awardInfo.getExtMap().get("address"));
            smallGiftInfo.setOrderId(UUID.randomUUID().toString());

            SmallGiftService smallGiftService = new SmallGiftService();
            Boolean aBoolean = smallGiftService.giveSmallGift(smallGiftInfo);
            if (aBoolean) {
                System.out.println("小礼品发放成功！");
            }
        }

    }
}
