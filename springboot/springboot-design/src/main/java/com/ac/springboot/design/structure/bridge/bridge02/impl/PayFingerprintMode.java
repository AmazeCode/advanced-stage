package com.ac.springboot.design.structure.bridge.bridge02.impl;

import com.ac.springboot.design.structure.bridge.bridge02.IPayMode;

/**
 * 指纹支付
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:05
 */
public class PayFingerprintMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        System.out.println("指纹支付，封控校验，指纹信息");
        return true;
    }
}
