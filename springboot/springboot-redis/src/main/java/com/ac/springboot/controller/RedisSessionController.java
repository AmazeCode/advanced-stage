package com.ac.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * session共享
 *
 * @Author: zhangyadong
 * @Date: 2022/10/24 21:34
 */
@RestController
@RequestMapping("session")
public class RedisSessionController {
    /*
        数据共享步骤：
        1、引入依赖
        <!-- 数据共享 -->
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
            <version>${session-redis.version}</version>
        </dependency>
        2、开启session共享
        @Configuration
        @EnableRedisHttpSession
        public class RedisSessionConfig {
        }
        3、创建controller验证
     */

    /**
     * 设置session的值
     * @param request
     * @return
     */
    @GetMapping("set")
    public Map set(HttpServletRequest request) {
        String id = request.getSession().getId();
        Map<String, String> vas = new HashMap<>();

        String key = "key";
        String value = "value";
        vas.put("id", id);
        vas.put(key, value);
        // 自定义session的值
        request.getSession().setAttribute(key, value);

        return vas;
    }

    /*
        验证方式：
        一个项目根据端口号启动两个实例,端口号分别为8080，8081
        http://localhost:8080/session/set 设置属性
        http://localhost:8081/session/get 可以直接访问属性
     */
    /**
     * 获取session的值
     * @param request
     * @return
     */
    @GetMapping("get")
    public Map get(HttpServletRequest request) {
        Map<String, Object> vas = new HashMap<>();

        // 遍历所有的session值
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String k = attributeNames.nextElement();
            Object va = request.getSession().getAttribute(k);
            vas.put(k, va);
        }

        vas.put("id", request.getSession().getId());

        return vas;
    }
}
