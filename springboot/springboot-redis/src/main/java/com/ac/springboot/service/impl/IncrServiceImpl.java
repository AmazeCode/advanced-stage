package com.ac.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Incr 实现限流,全局ID
 *
 * @Author: zhangyadong
 * @Date: 2022/10/24
 * @Version: v1.0
 */
@Service
@Slf4j
public class IncrServiceImpl {

    @Autowired
    StringRedisTemplate incrRedisTemplate;

    // 单位时间（秒）
    private static final Integer TIME = 5;
    // 允许访问上限次数
    private static final Integer MAX = 100;

    /**
     * 校验访问频率
     *
     * @param uniqueId 用于限流的唯一ID 可以是用户ID、或者客户端IP等
     * @return true：放行  false：拦截
     */
    public boolean frequency(String uniqueId) {
        String key = "redis:incr:" + uniqueId;
        Long increment = incrRedisTemplate.opsForValue().increment(key);
        if (increment == 1) {
            // 设置过期时间
            incrRedisTemplate.expire(key, TIME, TimeUnit.SECONDS);
        }

        if (increment <= MAX) {
            return true;
        }

        return false;
    }

    /**
     * 获取全局id
     *
     * @param key
     * @return
     */
    public Long globalID(String key) {
        // 访问一次递增一次，模拟生成全局id
        Long increment = incrRedisTemplate.opsForValue().increment(key);
        return increment;
    }
}
