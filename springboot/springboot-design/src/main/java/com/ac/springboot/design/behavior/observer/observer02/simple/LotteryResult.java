package com.ac.springboot.design.behavior.observer.observer02.simple;

import java.time.LocalDateTime;

/**
 * 开奖结果封装类
 * @Author: zhangyadong
 * @Date: 2022/12/17 15:05
 */
public class LotteryResult {

    private String uId;// 用户id

    private String msg;// 摇号信息

    public LocalDateTime dateTime;// 业务时间

    public LotteryResult(String uId, String msg, LocalDateTime dateTime) {
        this.uId = uId;
        this.msg = msg;
        this.dateTime = dateTime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LotteryResult{" +
                "uId='" + uId + '\'' +
                ", msg='" + msg + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
