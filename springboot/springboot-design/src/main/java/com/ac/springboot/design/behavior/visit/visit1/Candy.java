package com.ac.springboot.design.behavior.visit.visit1;

import java.time.LocalDate;

/**
 * 糖果类
 * @Author: zhangyadong
 * @Date: 2022/12/25 10:02
 */
public class Candy extends Product implements Acceptable {

    public Candy(String name, LocalDate productDate, double price) {
        super(name, productDate, price);
    }

    @Override
    public void accept(Visit visit) {
        // 在accept方法中调用访问者，并将自己 this 传递回去。
        visit.visit(this);
    }
}
