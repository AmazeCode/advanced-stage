package com.ac.springboot.design.behavior.state.state1;

/**
 * 上下文类
 * @Author: zhangyadong
 * @Date: 2022/12/24 18:29
 */
public class Context {

    // 维持一个对状态对象的引用
    private State currentState;

    public Context() {
        this.currentState = null;
    }

    public Context(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public String toString() {
        return "Context{" +
                "currentState=" + currentState +
                '}';
    }
}
