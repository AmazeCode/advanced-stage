package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/18 10:32
 */
@SpringBootTest
public class ObserverWholeTest {

    /**
     * 摇号、发送短信、记录MQ 使用观察者重构逻辑测试
     */
    @Test
    public void observerTest() {
        LotteryService ls = new LotteryServiceImpl();
        LotteryResult lotteryResult = ls.lotteryAndMsg("121423526366");
        System.out.println(lotteryResult);
    }
}
