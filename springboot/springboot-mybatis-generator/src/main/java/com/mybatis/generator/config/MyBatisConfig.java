package com.mybatis.generator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: MyBatis配置类
 * @author: AmazeCode
 * @date: 2024/5/12 10:17
 */
@Configuration
@MapperScan({"com.mybatis.generator.mapper","com.mybatis.generator.dao"})
public class MyBatisConfig {
}
