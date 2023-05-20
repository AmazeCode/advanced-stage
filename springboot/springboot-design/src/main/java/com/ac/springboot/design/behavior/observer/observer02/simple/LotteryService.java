package com.ac.springboot.design.behavior.observer.observer02.simple;

/**
 * 开奖服务接口
 * @Author: zhangyadong
 * @Date: 2022/12/17 15:03
 */
public interface LotteryService {

    // 开奖之后的业务操作
    public LotteryResult lottery(String uId);
}
