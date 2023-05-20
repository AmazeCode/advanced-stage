package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryImpl;
import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;

/**
 * 事件监听接口(观察者)
 * @Author: zhangyadong
 * @Date: 2022/12/18 9:51
 */
public interface EventListener {

    void doEvent(LotteryResult result);
}
