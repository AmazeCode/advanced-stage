package com.ac.springboot.controller;

import cn.hutool.json.JSONObject;
import com.ac.springboot.aop.RedissonLockAnnotation;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redisson 分布式所测试
 * @Author: zhangyadong
 * @Date: 2022/10/25 23:24
 */
@RestController
public class RedissonTestController {

    @Autowired
    private RedissonClient redissonClient;

    /*
        测试参数：
        {
            "productName":"product1",
            "platFormName":"JcShop",
            "userId":"123456",
            "amount":"15",
            "count":1
        }
     */
    @PostMapping(value = "testLock", consumes = "application/json")
    @RedissonLockAnnotation(lockRedisKey = "productName,platFormName")
    public String testLock(@RequestBody JSONObject params) throws InterruptedException {
        /**
         * 分布式锁key=params.getString("productName")+params.getString("platFormName");
         * productName 产品名称  platFormName 平台名称 如果都一致,那么分布式锁的key就会一直,那么就能避免并发问题
         */
        // 业务处理

        try {
            System.out.println("接收到的参数："+params.toString());
            System.out.println("执行相关业务...");
            System.out.println("执行相关业务.....");

            System.out.println("执行相关业务......");

        } catch (Exception e) {
            System.out.println("已进行日志记录");
        }

        return "success";
    }

    /*
        redisson分布式锁使用步骤：
        1、引入依赖
           <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson-spring-boot-starter</artifactId>
            <version>${springboot.redisson.version}</version>
        </dependency>
        2、新建配置文件redisson-config.yml
        3、新创建接口DistributeLocker和接口实现类RedissonDistributeLocker
        4、创建工具类RedissonLockUtils
        5、如果使用注解保证分布式锁,可以先创建注解RedissonLockAnnotation,然后再创建注解实现
        6、使用注解参考RedissonTestController的testLock方法使用案例
     */
}
