package com.ac.springboot.design.create.prototype.unprototype;

/**
 * 广告信模板类
 * @Author: zhangyadong
 * @Date: 2022/11/28 21:34
 */
public class AdvTemplate {

    // 广告信名称
    private String advSubject = "xx银行本月还款达标，可抽iPhone 13等好礼";

    // 广告信内容
    private String advContent = "达标用户请在2022年11月30日到2022年12月30号参与抽奖......";

    public String getAdvSubject() {
        return advSubject;
    }

    public void setAdvSubject(String advSubject) {
        this.advSubject = advSubject;
    }

    public String getAdvContent() {
        return advContent;
    }

    public void setAdvContent(String advContent) {
        this.advContent = advContent;
    }
}
