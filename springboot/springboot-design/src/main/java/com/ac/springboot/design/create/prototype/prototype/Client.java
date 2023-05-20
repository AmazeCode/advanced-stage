package com.ac.springboot.design.create.prototype.prototype;

import com.ac.springboot.design.create.prototype.prototype.Mail;

/**
 * 业务场景
 * @Author: zhangyadong
 * @Date: 2022/11/28 21:43
 */
public class Client {

    // 发送邮件的数量
    public static int MAX_COUNT = 6;

    // 发送邮件
    public static void sendMail(Mail mail) {
        System.out.println("标题：" + mail.getSubject() + "\t 收件人：" + mail.getReceiver() + "\t ....发送成功！");
    }
}
