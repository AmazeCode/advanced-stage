package com.ac.springboot.restful;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @Description: SpringBoot启动类
 *  @author: zhangyadong
 *  @Date: 2019/11/15 13:59
 *  @version: V1.0
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan(value = "com.ac.springboot.restful.dao")
public class SpringBootRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestfulApplication.class, args);
    }
}
