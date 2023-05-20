package com.ac.springboot.design.behavior.strategy.strategy5;

import com.ac.springboot.design.behavior.strategy.strategy5.Receipt;
import com.ac.springboot.design.behavior.strategy.strategy5.ReceiptBuilder;
import com.ac.springboot.design.behavior.strategy.strategy5.ReceiptHandleStrategy;
import com.ac.springboot.design.behavior.strategy.strategy5.ReceiptHandleStrategyFactory;
import com.ac.springboot.design.behavior.strategy.strategy5.ReceiptStrategyContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 13:53
 */
@SpringBootTest
public class Strategy5Test {

    /**
     * 通过反射冬天生成策略测试
     */
    @Test
    public void strategy5Test() {
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
