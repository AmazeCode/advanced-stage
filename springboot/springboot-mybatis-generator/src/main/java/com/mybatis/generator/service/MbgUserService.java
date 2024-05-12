package com.mybatis.generator.service;

import com.mybatis.generator.model.MbgUser;

import java.util.List;

/**
 * @description: 用户信息接口
 * @author: AmazeCode
 * @time: 2024/5/12 19:27
 */
public interface MbgUserService {

    /**
     * @description: 获取所有用户信息
     * @param
     * @return: java.util.List<com.mybatis.generator.model.MbgUser>
     * @author: AmazeCode
     * @date: 2024/5/12 19:48
     */
    List<MbgUser> getAll();
}
