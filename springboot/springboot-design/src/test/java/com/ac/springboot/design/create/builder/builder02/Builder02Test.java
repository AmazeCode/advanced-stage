package com.ac.springboot.design.create.builder.builder02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/27 22:39
 */
@SpringBootTest
public class Builder02Test {

    @Test
    public void buildChainTest() {

        // 获取连接对象
        RabbitmqClient3 client3 = new RabbitmqClient3.Builder()
                .setHost("127.0.0.1")
                .setMode(1)
                .setPort(5672)
                .setQueue("queue3")
                .build();
        client3.sendMessage("Test-Msg");
    }
}
