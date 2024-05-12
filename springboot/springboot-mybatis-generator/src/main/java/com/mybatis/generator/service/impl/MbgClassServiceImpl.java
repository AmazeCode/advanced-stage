package com.mybatis.generator.service.impl;

import com.mybatis.generator.mapper.MbgClassMapper;
import com.mybatis.generator.model.MbgClass;
import com.mybatis.generator.model.MbgClassExample;
import com.mybatis.generator.service.MbgClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 班级信息接口实现
 * @author: AmazeCode
 * @date: 2024/5/12 19:28
 */
@Service
public class MbgClassServiceImpl implements MbgClassService {

    @Resource
    private MbgClassMapper mbgClassMapper;

    @Override
    public List<MbgClass> getAllClass() {
        MbgClassExample example = new MbgClassExample();
        List<MbgClass> mbgClasses = mbgClassMapper.selectByExample(example);
        return mbgClasses;
    }
}
