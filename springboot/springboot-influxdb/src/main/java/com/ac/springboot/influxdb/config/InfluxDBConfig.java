package com.ac.springboot.influxdb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * InfluxDB 配置类
 * @author AmazeCode
 * @version 1.0
 * @date 2023/3/12 16:04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.influx")
public class InfluxDBConfig {

    /**
     * 连接地址
     */
    public String url;

    /**
     * 用户
     */
    public String user;

    /**
     * 密码
     */
    public String password;

    /**
     * 数据库
     */
    public String database;
}
