package com.ac.springboot.design.behavior.strategy.strategy5;

/**
 * 具体策略类
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:13
 */
public class MT1101ReceiptHandleStrategy implements ReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1101：" + receipt.getMessage());
    }
}
