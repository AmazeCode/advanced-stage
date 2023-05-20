package com.ac.springboot.messge;

import org.springframework.stereotype.Service;

/**
 * 消息接收
 *
 * @Author: zhangyadong
 * @Date: 2022/10/24
 * @Version: v1.0
 */
@Service
public class RedisReceiver {

    public void receiveMessage(String message) {
        System.out.println("接收消息：" + message);
    }
}
