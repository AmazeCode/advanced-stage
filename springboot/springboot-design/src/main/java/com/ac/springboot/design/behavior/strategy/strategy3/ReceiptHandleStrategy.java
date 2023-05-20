package com.ac.springboot.design.behavior.strategy.strategy3;

import com.ac.springboot.design.behavior.strategy.strategy2.Receipt;

/**
 * 回执处理策略接口
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:12
 */
public interface ReceiptHandleStrategy {

    void handleReceipt(Receipt receipt);
}
