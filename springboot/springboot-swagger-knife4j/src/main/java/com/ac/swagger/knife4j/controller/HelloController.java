package com.ac.swagger.knife4j.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api("测试接口")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "查询接口")
    @GetMapping
    public String get() {
        return "hello";
    }

    @ApiOperation(value = "设置接口")
    @PostMapping("/{name}")
    public String set(@PathVariable("name") String name) {
        return name;
    }

    @ApiOperation(value = "接收参数接口")
    @PostMapping()
    public String receive(@ApiParam("value") @RequestParam(value = "value") String value) {
        return value;
    }
}
