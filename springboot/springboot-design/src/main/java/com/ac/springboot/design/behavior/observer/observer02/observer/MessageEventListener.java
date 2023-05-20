package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;

/**
 * 短信发送事件监听
 * @Author: zhangyadong
 * @Date: 2022/12/18 9:52
 */
public class MessageEventListener implements EventListener {

    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("发送短信通知，用户ID：" + result.getuId() + ",您的摇号结果为：" + result.getMsg());
    }
}
