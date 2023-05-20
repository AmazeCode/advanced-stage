package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.DrawHouseService;
import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;

import java.time.LocalDateTime;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/18 10:25
 */
public class LotteryServiceImpl extends LotteryService {

    // 注入摇号服务
    private DrawHouseService houseService = new DrawHouseService();

    @Override
    public LotteryResult lottery(String uId) {
        // 1、摇号
        String result = houseService.lots(uId);
        return new LotteryResult(uId, result, LocalDateTime.now());
    }
}
