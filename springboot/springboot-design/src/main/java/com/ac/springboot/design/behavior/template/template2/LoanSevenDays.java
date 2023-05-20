package com.ac.springboot.design.behavior.template.template2;

/**
 * 借款七天
 * @Author: zhangyadong
 * @Date: 2022/12/23 21:59
 */
public class LoanSevenDays extends Account{

    @Override
    public void calculate() {
        System.out.println("借款周期7天，无利息！（仅收取贷款金额的1%的服务费）");
    }

    @Override
    public void display() {
        System.out.println("7日内借款无利息！");
    }
}
