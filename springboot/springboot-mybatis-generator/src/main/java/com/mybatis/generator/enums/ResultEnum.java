package com.mybatis.generator.enums;

import com.mybatis.generator.common.IResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 结果枚举
 * @author: AmazeCode
 * @date: 2024/3/24 20:50
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements IResult {

    SUCCESS(200, "请求成功"),
    FAILED(500, "请求失败");

    private int code;

    private String message;
}
