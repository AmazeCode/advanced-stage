package com.ac.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 *  @Description: 自定义的健康指示器
 *  @author: zhangyadong
 *  @Date: 2020/8/9 11:18
 *  @version: V1.0
 */
@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.down().withDetail("message","出错了").build();
    }

    //访问链接:http://localhost:8080/actuator/health/custom
}
