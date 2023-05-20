package com.ac.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 数据共享配置
 *
 * @Author: zhangyadong
 * @Date: 2022/10/24 21:29
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {


}
