package com.ac.springboot.design.structure.bridge.bridge02.impl;

import com.ac.springboot.design.structure.bridge.bridge02.IPayMode;

/**
 * 密码支付
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:08
 */
public class PayCypherMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        System.out.println("密码支付，封控校验--环境安全");
        return true;
    }
}
