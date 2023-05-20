package com.ac.springboot.design.create.factory.lower.entity;

/**
 * 打折券详细信息封装实体类
 * @Author: zhangyadong
 * @Date: 2022/11/25 15:48
 */
public class DiscountResult {

    private String status;// 状态码

    private String message;// 信息

    public DiscountResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
