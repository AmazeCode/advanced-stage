package com.ac.springboot.design.behavior.template.template2;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/23 22:02
 */
public class LoanOneMonth extends Account{

    @Override
    public void calculate() {
        System.out.println("借款周期30天，利息为借款总额的10%！");
    }
}
