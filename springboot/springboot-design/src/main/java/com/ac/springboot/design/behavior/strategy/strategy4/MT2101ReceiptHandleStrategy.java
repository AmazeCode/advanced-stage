package com.ac.springboot.design.behavior.strategy.strategy4;

import com.ac.springboot.design.behavior.strategy.strategy4.Receipt;

/**
 * 具体策略类
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:15
 */
public class MT2101ReceiptHandleStrategy implements ReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101：" + receipt.getMessage());
    }
}
