package com.mybatis.generator;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @Description: springboot 启动类
 * @author: zhangyadong
 * @Date: 2019/11/18 0018 下午 4:52
 * @version: V1.0
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.mybatis.generator.mapper")
public class SpringBootMyBatisGeneratorApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(SpringBootMyBatisGeneratorApplication.class,args);
        log.info("swagger-ui接口文档地址:http://{}:8080/swagger-ui.html", Inet4Address.getLocalHost().getHostAddress());
        log.info("knife4j接口文档地址:http://{}:8080/doc.html", Inet4Address.getLocalHost().getHostAddress());
    }
}
