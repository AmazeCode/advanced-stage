package com.mybatis.generator.mapper;

import com.mybatis.generator.model.MbgUser;
import com.mybatis.generator.model.MbgUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MbgUserMapper {
    long countByExample(MbgUserExample example);

    int deleteByExample(MbgUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MbgUser row);

    int insertSelective(MbgUser row);

    List<MbgUser> selectByExample(MbgUserExample example);

    MbgUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MbgUser row, @Param("example") MbgUserExample example);

    int updateByExample(@Param("row") MbgUser row, @Param("example") MbgUserExample example);

    int updateByPrimaryKeySelective(MbgUser row);

    int updateByPrimaryKey(MbgUser row);
}