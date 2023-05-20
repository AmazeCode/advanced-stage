package com.ac.springboot.design.behavior.expression.expression2;

/**
 * 数字表达式
 * @Author: zhangyadong
 * @Date: 2022/12/25 14:42
 */
public class NumExpression implements Expression{

    private long number;

    public NumExpression(long number) {
        this.number = number;
    }

    public NumExpression(String number) {
        this.number = Long.parseLong(number);
    }

    @Override
    public long interpret() {
        return this.number;
    }
}
