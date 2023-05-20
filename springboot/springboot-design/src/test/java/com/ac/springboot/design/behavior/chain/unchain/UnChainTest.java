package com.ac.springboot.design.behavior.chain.unchain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 15:03
 */
@SpringBootTest
public class UnChainTest {

    @Test
    public void unchainTest() throws ParseException {
        AuthController authController = new AuthController();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2023-11-12 00:00:00");

        // 模拟审核申请及审批操作
        AuthInfo info = authController.doAuth("研发小周", "100001000010000",date);
        System.out.println("当前审核状态：" + info.getInfo());

        AuthService.auth("1000013","100001000010000");
        System.out.println("三级审批负责人审批完成，审批人：王工");

        System.out.println("=====================================");
        AuthInfo info2 = authController.doAuth("研发小周", "100001000010000",date);
        System.out.println("当前审核状态：" + info2.getInfo());

        AuthService.auth("1000012","100001000010000");
        System.out.println("二级审批负责人审批完成，审批人：周经理");

        System.out.println("======================================");
        AuthInfo info3 = authController.doAuth("研发小周", "100001000010000",date);
        System.out.println("当前审核状态：" + info3.getInfo());

        AuthService.auth("1000012","100001000010000");
        System.out.println("一级审批负责人审批完成，审批人：罗总");
    }
}
