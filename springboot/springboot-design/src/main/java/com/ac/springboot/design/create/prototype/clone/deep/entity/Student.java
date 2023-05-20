package com.ac.springboot.design.create.prototype.clone.deep.entity;

import java.io.Serializable;

/**
 * 使用序列化必须要实现
 * @Author: zhangyadong
 * @Date: 2022/11/28 13:13
 */
public class Student implements Serializable {

    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
