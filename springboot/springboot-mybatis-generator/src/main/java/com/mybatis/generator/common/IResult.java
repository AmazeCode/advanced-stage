package com.mybatis.generator.common;

/**
 * @description: 定义返回数据结构
 * @author: AmazeCode
 * @time: 2024/3/24 20:53
 */
public interface IResult {

    /**
     * @description: 获取状态码
     * @param
     * @return: int
     * @author: AmazeCode
     * @date: 2024/3/24 21:05
     */
    int getCode();
    
    /**
     * @description: 获取状态描述
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2024/3/24 21:06
     */
    String getMessage();

}