package com.ac.springboot.design.behavior.chain.chain1;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 14:27
 */
public class HandlerA extends Handler {

    @Override
    public void handler(RequestData requestData) {
        System.out.println("HandlerA 执行代码逻辑！ 处理:" + requestData.getData());

        requestData.setData(requestData.getData().replace("A",""));

        // 判断是否继续向后调用处理器
        if (successor != null) {
            successor.handler(requestData);
        } else {
            System.out.println("执行中止");
        }
    }
}
