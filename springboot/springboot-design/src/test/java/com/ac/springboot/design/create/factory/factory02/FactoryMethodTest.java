package com.ac.springboot.design.create.factory.factory02;

import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory02.controller.DeliverController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试-工厂方法
 * @Author: zhangyadong
 * @Date: 2022/11/26 16:47
 */
@SpringBootTest
public class FactoryMethodTest {

    DeliverController deliverController = new DeliverController();

    @Test
    public void test01(){
        // 1、发放打折券

        AwardInfo info = new AwardInfo();
        info.setUid("1001");
        info.setAwardTypes(1);
        info.setAwardNumber("DEL12345");

        ResponseResult result = deliverController.awardToUser(info);
        System.out.println(result);
    }

    @Test
    public void test03(){
        // 3、发放小礼品
        AwardInfo info = new AwardInfo();
        info.setUid("1003");
        info.setAwardTypes(3);
        info.setAwardNumber("DEL12345");
        Map<String,String> map = new HashMap<>();
        map.put("username","大眼");
        map.put("phone","13500000001");
        map.put("address","天通苑");
        info.setExtMap(map);

        ResponseResult result = deliverController.awardToUser(info);
        System.out.println(result);
    }
}
