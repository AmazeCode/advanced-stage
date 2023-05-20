package com.ac.springboot.design.structure.bridge.bridge02.impl;

import com.ac.springboot.design.structure.bridge.bridge02.IPayMode;

/**
 * 刷脸支付
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:06
 */
public class PayFaceMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        System.out.println("人脸支付，封控校验-->脸部识别");
        return true;
    }
}
