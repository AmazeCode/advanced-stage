package com.ac.spring.aop.controller;

import com.ac.spring.aop.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  @Description: 面向切面
 *  @author: zhangyadong
 *  @Date: 2020/8/8 17:20
 *  @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/aspect")
public class AspectController {

    /**
     * @Description 普通环绕通知
     * @params [message]
     * @return com.ac.spring.aop.util.Result
     * @author zhangyadong
     * @date 2020/8/8 17:27
     */
    @GetMapping
    public Result aspect(String message) {
        log.info("aspect controller");
        return Result.sucess(message);
    }

    /**
     * @Description 异常处理
     * @params []
     * @return com.ac.spring.aop.util.Result
     * @author zhangyadong
     * @date 2020/8/8 17:27
     */
    @GetMapping("/exception")
    public Result exception() {
        throw new RuntimeException("runtime exception");
    }

    /**
     * @Description 性能统计
     * @params [time]
     * @return com.ac.spring.aop.util.Result
     * @author zhangyadong
     * @date 2020/8/8 17:27
     */
    @GetMapping("/sleep/{time}")
    public Result sleep(@PathVariable("time") long time) {
        log.info("sleep");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error("error",e);
        }
        if (time == 1000) {
            throw new RuntimeException("runtime exception");
        }
        log.info("wake up");
        return Result.sucess("wake up");
    }

}
