package com.ac.springboot.design.create.prototype.clone.shallow.entity;

/**
 * @Author: zhangyadong
 * @Date: 2022/11/28 13:13
 */
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
