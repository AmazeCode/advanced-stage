package com.ac.springboot.design.structure.bridge.bridge02;

import java.math.BigDecimal;

/**
 * 支付抽象
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:10
 */
public abstract class Pay {

    // 桥接对象
    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    // 划账
    public abstract String transfer(String uId, String tradeId, BigDecimal amount);
}
