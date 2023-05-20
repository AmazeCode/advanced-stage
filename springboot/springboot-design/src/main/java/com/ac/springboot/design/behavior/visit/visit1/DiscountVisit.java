package com.ac.springboot.design.behavior.visit.visit1;

import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * 折扣计价访问者
 * @Author: zhangyadong
 * @Date: 2022/12/25 10:08
 */
public class DiscountVisit implements Visit{

    private LocalDate billDate;

    public DiscountVisit(LocalDate billDate) {
        this.billDate = billDate;
        System.out.println("结算日期：" + billDate);
    }

    @Override
    public void visit(Candy candy) {
        System.out.println("糖果：" + candy.getName());
        // 糖果大于180天，禁止售卖，否则糖果一律九折
        long days = billDate.toEpochDay() - candy.getProductDate().toEpochDay();

        if (days > 180) {
            System.out.println("超过半年的糖果，请勿食用！");
        }else{
            double realPrice = candy.getPrice() * 0.9;
            System.out.println("糖果打折后的价格为：" +
                    NumberFormat.getCurrencyInstance().format(realPrice));
        }
    }

    @Override
    public void visit(Wine wine) {
        System.out.println("酒类：" + wine.getName());
        System.out.println("原价售卖：" +
                NumberFormat.getCurrencyInstance().format(wine.getPrice()));

    }

    @Override
    public void visit(Fruit fruit) {
        System.out.println("水果：" + fruit.getName());
        long days = billDate.toEpochDay() - fruit.getProductDate().toEpochDay();

        double rate = 0;
        if (days > 7) {
            System.out.println("超过七天的水果，请勿食用！");
        }else if (days > 3) {
            rate = 0.5;
        }else {
            rate = 1;
        }

        double realPrice = fruit.getPrice() * fruit.getWeight() * rate;

        System.out.println("水果价格：" +
                NumberFormat.getCurrencyInstance().format(realPrice));
    }
}
