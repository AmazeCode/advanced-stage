package com.ac.springboot.design.behavior.visit.visit1;

import java.time.LocalDate;

/**
 * 酒水类
 * @Author: zhangyadong
 * @Date: 2022/12/25 10:03
 */
public class Wine extends Product implements Acceptable {

    public Wine(String name, LocalDate productDate, double price) {
        super(name, productDate, price);
    }

    @Override
    public void accept(Visit visit) {
        visit.visit(this);
    }
}
