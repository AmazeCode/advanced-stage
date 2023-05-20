package com.ac.springboot.design.behavior.observer.observer02.simple;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryImpl;
import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;
import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/17 15:18
 */
@SpringBootTest
public class Observer02Test {

    @Test
    public void simpleTest() {
        LotteryService lotteryService = new LotteryImpl();
        LotteryResult lottery = lotteryService.lottery("1213413516677");
        System.out.println(lottery);
    }
}
