package com.ac.springboot.design.behavior.chain.chain1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 14:32
 */
@SpringBootTest
public class Chain1Test {

    @Test
    public void chain1Test() {
        // 创建处理者
        Handler h1 = new HandlerA();
        Handler h2 = new HandlerB();
        Handler h3 = new HandlerC();

        // 创建处理链
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        RequestData requestData = new RequestData("请求数据：ABCD");
        // 调用处理链头部的方法
        h1.handler(requestData);
    }
}
