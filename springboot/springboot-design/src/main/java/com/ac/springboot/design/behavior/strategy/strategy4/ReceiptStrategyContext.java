package com.ac.springboot.design.behavior.strategy.strategy4;

import com.ac.springboot.design.behavior.strategy.strategy4.Receipt;

/**
 * 上下文类，持有策略接口，决定执行哪一个具体的策略
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:16
 */
public class ReceiptStrategyContext {

    private ReceiptHandleStrategy receiptHandleStrategy;

    public ReceiptStrategyContext() {
    }

    public ReceiptStrategyContext(ReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    // 调用策略类中的方法
    public void handleReceipt(Receipt receipt) {
        receiptHandleStrategy.handleReceipt(receipt);
    }

    public ReceiptHandleStrategy getReceiptHandleStrategy() {
        return receiptHandleStrategy;
    }

    public void setReceiptHandleStrategy(ReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }
}
