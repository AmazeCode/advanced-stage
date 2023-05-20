package com.ac.springboot.design.behavior.visit.visit1;

/**
 * 接收者接口
 * @Author: zhangyadong
 * @Date: 2022/12/25 11:08
 */
public interface Acceptable {

    // 接收所有Visitor访问者的子类
    void accept(Visit visit);
}
