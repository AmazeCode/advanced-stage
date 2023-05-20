package com.ac.springboot.design.behavior.mediator.mediator1;

/**
 * 抽象中介者
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:22
 */
public interface Mediator {

    // 处理同事对象注册与转发同事对象信息的方法
    void apply(String key);
}
