package com.ac.springboot.design.behavior.chain.chain2;

import cn.hutool.json.JSONUtil;
import com.ac.springboot.design.behavior.chain.unchain.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 15:39
 */
@SpringBootTest
@Slf4j
public class Chain2Test {

    @Test
    public void chain2Test() throws ParseException {
        AuthLink authLink = new Level3AuthLink("1000013", "李工")
                .appendNext(new Level2AuthLink("1000012", "王经理")
                        .appendNext(new Level1AuthLink("1000011", "罗总")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = sdf.parse("2022-11-18 23:49:46");

        log.info("测试结果：{}", JSONUtil.toJsonStr(authLink.doAuth("研发牛马","1000998004813441",currentDate)));

        // 模拟三级负责人审批
        AuthService.auth("1000013","1000998004813441");
        log.info("测试结果：{}","模拟三级负责人审批，王工");
        log.info("测试结果：{}",JSONUtil.toJsonStr(JSONUtil.toJsonStr(authLink.doAuth("研发牛马","1000998004813441",currentDate))));

        // 模拟二级负责人审批
        AuthService.auth("1000012","1000998004813441");
        log.info("测试结果：{}","模拟二级负责人审批，张经理");
        log.info("测试结果：{}",JSONUtil.toJsonStr(JSONUtil.toJsonStr(authLink.doAuth("研发牛马","1000998004813441",currentDate))));

        // 模拟一级负责人审批
        AuthService.auth("1000011","1000998004813441");
        log.info("测试结果：{}","模拟一级负责人审批，罗总");
        log.info("测试结果：{}",JSONUtil.toJsonStr(JSONUtil.toJsonStr(authLink.doAuth("研发牛马","1000998004813441",currentDate))));
    }
}
