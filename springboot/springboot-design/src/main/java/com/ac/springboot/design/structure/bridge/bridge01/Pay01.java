package com.ac.springboot.design.structure.bridge.bridge01;

import java.math.BigDecimal;

/**
 * 支付
 * @Author: zhangyadong
 * @Date: 2022/12/14 17:41
 */
public class Pay01 {

    /**
     * @description: 支付接口
     * @param: uId   用户id
     * @param: tradeId 交易id
     * @param: amount  交易金额
     * @param: channelType 渠道类型：1 微信 2 支付宝
     * @param: modeType   支付类型：1 密码 2 人脸 3 指纹
     * @return: boolean
     * @author: zhangyadong
     * @date: 2022/12/14 17:44
     */
    public boolean doPay(String uId, String tradeId, BigDecimal amount, int channelType, int modeType) {

        // 微信支付
        if (channelType == 1) {
            System.out.println("微信渠道支付。。。。。。。");
            if (modeType == 1) {
                System.out.println("密码支付");
            }else if (modeType == 2) {
                System.out.println("人脸支付");
            }else if (modeType == 3) {
                System.out.println("指纹支付");
            }
        }

        // 支付宝支付
        if (channelType == 2) {
            System.out.println("支付宝渠道支付。。。。。。。");
            if (modeType == 1) {
                System.out.println("密码支付");
            }else if (modeType == 2) {
                System.out.println("人脸支付");
            }else if (modeType == 3) {
                System.out.println("指纹支付");
            }
        }
        return true;
    }
}
