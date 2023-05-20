package com.ac.springboot.controller;

import com.ac.springboot.annotation.ResultResponseBody;
import com.ac.springboot.exception.ResultException;
import com.ac.springboot.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/21
 * @Version: v1.0
 */
@RestController
@RequestMapping("/hello")
@ResultResponseBody
public class HelloController {

    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    /**
     * 封装统一返回步骤:
     * 1、自定义返回状态枚举ResultStatus
     * 2、自定义统一返回对象
     * 3、自定义异常对象ResultException
     * 4、自定义注解ResultResponseBody继承ResponseBody
     * 5、自定义ResponseResultBodyAdvice实现ResponseBodyAdvice,统一封装返回,以及统一异常处理
     *
     * 以上处理方式在Spring Cloud OpenFeign 继承模式具有了侵入性
     * 解决方式参考博客：https://blog.csdn.net/qq_34347620/article/details/124295302?spm=1001.2014.3001.5502
     */

    @GetMapping("hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    /** 测试重复包裹 */
    @GetMapping("result")
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new ResultException();
    }
}
