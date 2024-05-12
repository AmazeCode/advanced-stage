package com.mybatis.generator.controller;

import com.mybatis.generator.annotation.NotControllerResponseAdvice;
import com.mybatis.generator.model.MbgClass;
import com.mybatis.generator.model.MbgUser;
import com.mybatis.generator.service.MbgClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: AmazeCode
 * @date: 2024/5/12 19:32
 */
@Api(value = "班级信息", tags = {"MbgUserController"})
@RestController
@RequestMapping(("/mbg-class"))
public class MbgClassController {

    @Resource
    private MbgClassService mbgClassService;

    @NotControllerResponseAdvice
    @ApiOperation(value = "获取所有班级集合")
    @GetMapping
    public List<MbgClass> getAllMbgUser(){
        return mbgClassService.getAllClass();
    }
}
