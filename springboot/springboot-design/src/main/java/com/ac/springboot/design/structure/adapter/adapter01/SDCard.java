package com.ac.springboot.design.structure.adapter.adapter01;

/**
 * SD卡接口
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:19
 */
public interface SDCard {

    // 读取SD卡
    String readSD();

    // 写入SD卡
    void writeSD(String msg);
}
