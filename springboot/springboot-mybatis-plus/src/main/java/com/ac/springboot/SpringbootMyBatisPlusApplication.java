package com.ac.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: springboot 启动类
 * @author: zhangyadong
 * @Date: 2019/11/18 0018 下午 4:52
 * @version: V1.0
 */
@SpringBootApplication
@MapperScan("com.ac.springboot.mapper")
public class SpringbootMyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMyBatisPlusApplication.class,args);
    }
}
