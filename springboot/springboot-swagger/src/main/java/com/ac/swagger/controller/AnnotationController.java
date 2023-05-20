package com.ac.swagger.controller;

import com.ac.swagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  @Description: 注解Controller(注解使用)
 *  @author: zhangyadong
 *  @Date: 2020/7/25 19:20
 *  @version: V1.0
 */
@Api(tags = "注解使用接口")
@Slf4j
@RestController
public class AnnotationController {

    @ApiOperation(value = "无注解方式")
    @GetMapping("/noannotation")
    public User noAnnotation(User user) {
        return user;
    }

    /**
     * @Description @RequestParam使用(四个参数name:参数名称,value：name 属性的别名,required：指定是否为必传参数（为 true 时不传会报错）,defaultValue：参数默认值)
     * @params [name, age]
     * @return com.ac.swagger.model.User
     * @author zhangyadong
     * @date 2020/7/25 19:30
     */
    @ApiOperation(value = "@RequestParam使用(四个参数name:参数名称,value：name 属性的别名,required：指定是否为必传参数（为 true 时不传会报错）,defaultValue：参数默认值)")
    @GetMapping("/requestparam")
    public User RequestParam(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    /**
     * @Description @PathVariable接收url参数使用（name：参数名称，value：name 属性的别名，required：指定是否为必传参数（为 true 时不传会报错））
     * @params [name, age]
     * @return com.ac.swagger.model.User
     * @author zhangyadong
     * @date 2020/7/25 19:34
     */
    @ApiOperation(value = "@PathVariable接收url参数使用（name：参数名称，value：name 属性的别名，required：指定是否为必传参数（为 true 时不传会报错））")
    @GetMapping("/pathvariable/{name}/{age}")
    public User PathVariable(@PathVariable String name,@PathVariable int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    /**
     * @Description @RequestBody使用（required：指定是否为必传参数（为 true 时不传会报错）@Valid验证数据格式）
     * @params [user]
     * @return com.ac.swagger.model.User
     * @author zhangyadong
     * @date 2020/7/25 19:37
     */
    @ApiOperation(value = "@RequestBody使用（required：指定是否为必传参数（为 true 时不传会报错））")
    @PostMapping("/requestbody")
    public User RequestBody(@RequestBody @Valid User user) {
        return user;
    }

    /**
     * @Description 测试拦截器
     * @params [name]
     * @return java.lang.String
     * @author zhangyadong
     * @date 2020/7/25 21:51
     */
    @ApiOperation(value = "拦截器测试")
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) @ApiParam("名字") String name) {
        if (name == null || "".equals(name)) {
            name = "Spring Boot";
        }
        log.info("测试拦截器");
        return "Hello "+name;
    }
}
