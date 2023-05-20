package com.ac.springboot.design.behavior.chain.chain1;

/**
 * 请求封装类
 * @Author: zhangyadong
 * @Date: 2022/12/24 14:24
 */
public class RequestData {

    private String data;

    public RequestData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
