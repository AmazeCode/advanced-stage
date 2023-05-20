package com.ac.springboot.design.create.factory.lower.service;

import com.ac.springboot.design.create.factory.lower.entity.DiscountResult;

/**
 * 打折券服务
 * @Author: zhangyadong
 * @Date: 2022/11/25 15:52
 */
public class DiscountService {

    public DiscountResult sendDiscount(String uid, String awardNumber) {
        System.out.println("向用户发送一张打折券："+ uid + "," + awardNumber);

        return new DiscountResult("200","发放打折券成功！");
    }
}
