package com.ac.springboot.design.create.factory.lower.service;

/**
 * 赠送优酷会员服务
 * @Author: zhangyadong
 * @Date: 2022/11/25 15:53
 */
public class YouKuMemberService {

    public void openMemeber(String bindMobile, String awardNumber) {
        System.out.println("发放优酷会员："+ bindMobile + "," + awardNumber);
    }
}
