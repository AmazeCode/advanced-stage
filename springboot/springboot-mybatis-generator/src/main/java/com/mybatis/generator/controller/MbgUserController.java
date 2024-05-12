package com.mybatis.generator.controller;

/**
 * @description: 用户信息控制层
 * @author: AmazeCode
 * @date: 2024/5/12 19:29
 */

import com.mybatis.generator.model.MbgUser;
import com.mybatis.generator.service.MbgUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "用户信息", tags = {"MbgUserController"})
@RestController
@RequestMapping(("/mbg-user"))
public class MbgUserController {

    @Resource
    private MbgUserService mbgUserService;

    @ApiOperation(value = "获取所有用户集合")
    @GetMapping
    public List<MbgUser> getAllMbgUser(){
        return mbgUserService.getAll();
    }










}
