package com.ac.springboot.design.behavior.strategy.strategy2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:05
 */
@SpringBootTest
public class Strategy2Test {

    @Test
    public void strategy2Test() {
        List<Receipt> receiptLis = ReceiptBuilder.getReceiptLis();

        for (Receipt receiptLi : receiptLis) {
            if ("MT1011".equals(receiptLi.getType())) {
                System.out.println("接收到MT1011的回执信息");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑A。。。。");
            }else if ("MT2101".equals(receiptLi.getType())) {
                System.out.println("接收到MT2101的回执信息");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑B。。。。");
            }else if ("MT4101".equals(receiptLi.getType())) {
                System.out.println("接收到MT2101的回执信息");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑C。。。。");
            }else if ("MT8104".equals(receiptLi.getType())) {
                System.out.println("接收到MT8104的回执信息");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑D。。。。");
            }
            // 。。。。
        }
    }
}
