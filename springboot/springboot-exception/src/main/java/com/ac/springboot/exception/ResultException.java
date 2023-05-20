package com.ac.springboot.exception;

import com.ac.springboot.contant.ResultStatus;
import lombok.Data;

/**
 * 自定义异常
 *
 * @Author: zhangyadong
 * @Date: 2022/11/22
 * @Version: v1.0
 */
@Data
public class ResultException extends Exception{

    /**
     * 状态码
     */
    private ResultStatus resultStatus;

    /**
     * 异常信息
     */
    private String message;

    public ResultException() {
        super();
        // 必须设置状态码，ResponseResultBodyAdvice handleResultException中对于改异常处理的处理需要状态码
        resultStatus = ResultStatus.INTERNAL_SERVER_ERROR;
    }

    public ResultException(ResultStatus resultStatus, String message) {
        super(message);
        this.resultStatus = resultStatus;
    }
}
