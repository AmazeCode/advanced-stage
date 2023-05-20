package com.ac.springboot.design.behavior.expression.expression2;

/**
 * 减法运算
 * @Author: zhangyadong
 * @Date: 2022/12/25 14:46
 */
public class SubExpression implements Expression{

    private Expression exp1;

    private Expression exp2;

    public SubExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public long interpret() {
        return exp1.interpret() - exp2.interpret();
    }
}
