package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;

/**
 * MQ消息发送事件监听
 * @Author: zhangyadong
 * @Date: 2022/12/18 9:55
 */
public class MQEventListener implements EventListener {

    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("记录用户的摇号结果（MQ），用户ID：" + result.getuId() + ",摇号结果：" + result.getMsg());
    }
}
