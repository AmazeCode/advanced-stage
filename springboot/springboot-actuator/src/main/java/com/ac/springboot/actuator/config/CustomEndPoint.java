package com.ac.actuator.config;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *  @Description: 自定义端点
 *  @author: zhangyadong
 *  @Date: 2020/8/9 11:20
 *  @version: V1.0
 */
@Component
@Endpoint(id = "customEndPoint")
public class CustomEndPoint {

    @ReadOperation
    public Map<String,String> read() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "自定义端点");
        return map;
    }

    @WriteOperation
    public Map<String,String> write(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("message", name + " 已被修改");
        return map;
    }

    @DeleteOperation
    public Map<String,String> delete(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("message", name + " 已被删除");
        return map;
    }

    //自定义端点get:http://localhost:8080/actuator/customEndPoint
    //自定义端点post(需要传参):http://localhost:8080/actuator/customEndPoint
    //自定义端点delete(需要传参):http://localhost:8080/actuator/customEndPoint
}
