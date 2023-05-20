package com.ac.swagger.controller;

import com.ac.swagger.ao.PersonAo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: HelloController
 * @author: zhangyadong
 * @Date: 2020/7/22 0022 下午 8:52
 * @version: V1.0
 */
@Api(tags = "Swagger使用Demo接口")
@RestController
public class SwaggerController {

    @ApiOperation("Hello World Spring Boot 方法")
    @ApiResponses({@ApiResponse(code=200,message="响应视图封装详见String")})
    @PostMapping("/")
    public String hello(@ApiParam(value = "请求参数封装详见[Person]") @RequestBody PersonAo personAo) {
        if (personAo != null && !"".equals(personAo.getUserName())) {
            return "Hello " + personAo.getUserName();
        }
        return "Hello Spring Boot";
    }

    /**
     *  1、接口文档访问地址：http://localhost:8080/swagger-ui.html
     */
}
