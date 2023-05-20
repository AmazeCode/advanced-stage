package com.ac.springboot.design.structure.bridge.bridge02;

/**
 * 适配器模式
 * 支付模式接口
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:02
 */
public interface IPayMode {

    // 安全校验功能：对各种支付模式进行封控校验操作
    boolean security(String uId);
}
