package com.ac.springboot.design.structure.bridge.bridge02.pay;

import com.ac.springboot.design.structure.bridge.bridge02.IPayMode;
import com.ac.springboot.design.structure.bridge.bridge02.Pay;

import java.math.BigDecimal;

/**
 * 支付渠道-支付宝
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:23
 */
public class ZfbPay extends Pay {

    public ZfbPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        System.out.println("支付宝渠道支付划账开始.....");
        boolean security = payMode.security(uId);
        System.out.println("支付宝渠道支付风险校验：" + uId + "," + tradeId + "," + security);

        if (!security) {
            System.out.println("支付宝渠道支付划账失败！");
            return "500";
        }

        System.out.println("支付宝渠道支付划账成功！金额：" + amount);
        return "200";
    }
}
