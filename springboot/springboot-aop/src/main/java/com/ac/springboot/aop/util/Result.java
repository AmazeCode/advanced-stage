package com.ac.spring.aop.util;

import lombok.Data;

/**
 *  @Description: 返回结果集
 *  @author: zhangyadong
 *  @Date: 2020/8/8 13:59
 *  @version: V1.0
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    /**
     * @Description 成功
     * @params []
     * @return com.ac.exception.common.Result
     * @author zhangyadong
     * @date 2020/8/8 14:01
     */
    public static Result sucess() {
        return sucess(null);
    }

    public static Result sucess(Object data) {
        Result result = new Result();
        result.setMessage(MessageEnum.SUCCESS.getMessage());
        result.setCode(MessageEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    /**
     * @Description 失败
     * @params [code, message]
     * @return com.ac.exception.common.Result
     * @author zhangyadong
     * @date 2020/8/8 14:01
     */
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

}
