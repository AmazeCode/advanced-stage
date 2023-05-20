package com.ac.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *  @Description: 启动类
 *  @author: zhangyadong
 *  @Date: 2019/11/24 21:03
 *  @version: V1.0
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduleApplication.class,args);
    }
}
