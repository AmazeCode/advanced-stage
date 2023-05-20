package com.ac.springboot.design.create.prototype.prototype;

import com.ac.springboot.design.create.prototype.prototype.AdvTemplate;
import com.ac.springboot.design.create.prototype.prototype.Client;
import com.ac.springboot.design.create.prototype.prototype.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/28 22:08
 */
@SpringBootTest
public class PrototypeTest {

    @Test
    public void sendMailTest() {

        int i = 0;

        // 定义模板
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xxx银行版权所有");

        while(i < com.ac.springboot.design.create.prototype.unprototype.Client.MAX_COUNT) {
            // 每封邮件不同的信息
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation("先生（女士）");
            Random random = new Random();
            int num = random.nextInt(999999999);
            cloneMail.setReceiver(num + "@qq.com");

            // 发送邮件
            Client.sendMail(cloneMail);
            i++;
        }
    }
}
