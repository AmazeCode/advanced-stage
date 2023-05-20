package com.ac.springboot.influxdb.controller;

import com.ac.springboot.influxdb.service.InfluxdbService;
import org.influxdb.dto.QueryResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AmazeCode
 * @version 1.0
 * @date 2023/3/12 16:16
 */
@RestController
@RequestMapping("influxdb")
public class InfluxdbController {

    @Resource
    InfluxdbService influxdbService;

    @GetMapping("")
    public Object list() {
        String command = "select * from host_cpu_usage_total";
        QueryResult query = influxdbService.query(command);
        List<Map<String, Object>> maps = influxdbService.queryResultProcess(query);
        return maps;
    }

    @PostMapping("")
    public Object add () {
        String measurement = "host_cpu_usage_total";
        Map<String,String> tags = new HashMap<>();
        tags.put("host_name","host2");
        tags.put("cpu_core","core0");
        Map<String, Object> fields = new HashMap<>();
        fields.put("cpu_usage",0.22);
        fields.put("cpu_idle",0.56);
        influxdbService.insert(measurement, tags, fields);
        return "OK";
    }
}
