package com.ac.springboot.design.structure.adapter.adapter01;

/**
 * TF卡接口
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:24
 */
public interface TFCard {

    // 读取TF卡
    String readTF();

    // 写入TF卡
    void writeTF(String msg);
}
