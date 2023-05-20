package com.ac.springboot.design.structure.bridge.bridge01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/14 17:49
 */
@SpringBootTest
public class Pay01Test {

    @Test
    public void pay01Test() {
        Pay01 p = new Pay01();
        System.out.println("测试：微信支付 --> 人脸支付方式");
        p.doPay("wx_001","100100", new BigDecimal(100),1,2);

        System.out.println();

        p.doPay("ZFB_001","200200", new BigDecimal(100),2,3);

    }
}
