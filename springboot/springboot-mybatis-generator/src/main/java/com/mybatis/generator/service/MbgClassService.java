package com.mybatis.generator.service;

import com.mybatis.generator.model.MbgClass;

import java.util.List;

/**
 * @description: 班级信息接口
 * @author: AmazeCode
 * @time: 2024/5/12 19:28
 */
public interface MbgClassService {

    /**
     * @description: 获取所有班级信息
     * @param
     * @return: java.util.List<com.mybatis.generator.model.MbgClass>
     * @author: AmazeCode
     * @date: 2024/5/12 20:20
     */
    List<MbgClass> getAllClass();
}
