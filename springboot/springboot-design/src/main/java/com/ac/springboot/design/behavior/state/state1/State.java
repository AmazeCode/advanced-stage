package com.ac.springboot.design.behavior.state.state1;

/**
 * 抽象状态接口(状态模式)
 * @Author: zhangyadong
 * @Date: 2022/12/24 18:27
 */
public interface State {

    // 声明抽象方法，不同具体状态类可以有不同的实现
    void handle(Context context);
}
