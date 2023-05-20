package com.ac.springboot.design.behavior.strategy.strategy4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:30
 */
@SpringBootTest
public class Strategy4Test {

    @Test
    public void strategy4Test() {
        // 模拟回执
        List<Receipt> receiptLis = ReceiptBuilder.getReceiptLis();

        // 策略上下文
        ReceiptStrategyContext context = new ReceiptStrategyContext();

        // 策略模式最主要的工作：将策略的 定义，创建，使用这三部分进行解耦
        for (Receipt receipt : receiptLis) {
            // 获取策略
            ReceiptHandleStrategyFactory.init();
            ReceiptHandleStrategy strategy = ReceiptHandleStrategyFactory.getStrategy(receipt.getType());

            // 设置策略
            context.setReceiptHandleStrategy(strategy);
            // 执行策略
            context.handleReceipt(receipt);

        }
    }
}
