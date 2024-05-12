package com.mybatis.generator.mapper;

import com.mybatis.generator.model.MbgClass;
import com.mybatis.generator.model.MbgClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MbgClassMapper {
    long countByExample(MbgClassExample example);

    int deleteByExample(MbgClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MbgClass row);

    int insertSelective(MbgClass row);

    List<MbgClass> selectByExample(MbgClassExample example);

    MbgClass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MbgClass row, @Param("example") MbgClassExample example);

    int updateByExample(@Param("row") MbgClass row, @Param("example") MbgClassExample example);

    int updateByPrimaryKeySelective(MbgClass row);

    int updateByPrimaryKey(MbgClass row);
}