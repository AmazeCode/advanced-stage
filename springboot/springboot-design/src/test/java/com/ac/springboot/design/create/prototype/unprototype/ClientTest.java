package com.ac.springboot.design.create.prototype.unprototype;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/28 21:48
 */
@SpringBootTest
public class ClientTest {

    @Test
    public void sendMailTest() {

        int i = 0;

        // 定义模板
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xxx银行版权所有");

        while(i < Client.MAX_COUNT) {
            // 每封邮件不同的信息
            mail.setAppellation("先生（女士）");
            Random random = new Random();
            int num = random.nextInt(999999999);
            mail.setReceiver(num + "@qq.com");

            // 发送邮件
            Client.sendMail(mail);
            i++;
        }
    }
}
