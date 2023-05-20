package com.ac.springboot.design.create.factory.factory01;

import com.ac.springboot.design.create.factory.factory01.controller.DeliverController;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;
import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试-简单工厂
 * @Author: zhangyadong
 * @Date: 2022/11/25 19:12
 */
@SpringBootTest
public class Factory01Test {

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
    public void test02(){
        // 2、发放优酷会员
        AwardInfo info = new AwardInfo();
        info.setUid("1002");
        info.setAwardTypes(2);
        info.setAwardNumber("DEL12345");
        Map<String,String> map = new HashMap<>();
        map.put("phone","13500000001");
        info.setExtMap(map);

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
