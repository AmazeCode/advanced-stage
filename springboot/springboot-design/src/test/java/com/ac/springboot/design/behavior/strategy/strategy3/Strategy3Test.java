package com.ac.springboot.design.behavior.strategy.strategy3;

import com.ac.springboot.design.behavior.strategy.strategy2.Receipt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:20
 */
@SpringBootTest
public class Strategy3Test {

    @Test
    public void strategy3Test() {
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext(new MT1101ReceiptHandleStrategy());
        receiptStrategyContext.handleReceipt(new Receipt("MT1101报文","MT1101"));
    }
}
