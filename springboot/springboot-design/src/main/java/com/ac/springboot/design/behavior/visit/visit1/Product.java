package com.ac.springboot.design.behavior.visit.visit1;

import java.time.LocalDate;

/**
 * 抽象商品父类
 * @Author: zhangyadong
 * @Date: 2022/12/25 10:00
 */
public abstract class Product {

    private String name;// 商品名称

    private LocalDate productDate;// 生产日期

    private double price;// 商品价格

    public Product(String name, LocalDate productDate, double price) {
        this.name = name;
        this.productDate = productDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductDate() {
        return productDate;
    }

    public void setProductDate(LocalDate productDate) {
        this.productDate = productDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
