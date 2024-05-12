package com.mybatis.generator.service.impl;

import com.mybatis.generator.mapper.MbgUserMapper;
import com.mybatis.generator.model.MbgUser;
import com.mybatis.generator.model.MbgUserExample;
import com.mybatis.generator.service.MbgUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户信息接口实现
 * @author: AmazeCode
 * @date: 2024/5/12 19:27
 */
@Service
public class MbgUserServiceImpl implements MbgUserService {

    @Resource
    private MbgUserMapper mbgUserMapper;

    @Override
    public List<MbgUser> getAll() {
        MbgUserExample example = new MbgUserExample();
        List<MbgUser> mbgUsers = mbgUserMapper.selectByExample(example);
        return mbgUsers;
    }
}
