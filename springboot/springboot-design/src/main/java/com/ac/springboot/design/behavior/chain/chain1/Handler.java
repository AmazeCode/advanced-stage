package com.ac.springboot.design.behavior.chain.chain1;

/**
 * 抽象的处理者
 * @Author: zhangyadong
 * @Date: 2022/12/24 14:25
 */
public abstract class Handler {

    // 后继处理者的引用
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handler(RequestData requestData);
}
