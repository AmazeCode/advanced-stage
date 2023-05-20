package com.ac.springboot.design.behavior.memento.memento1;

/**
 * 发起人角色
 * @Author: zhangyadong
 * @Date: 2022/12/25 11:38
 */
public class Originator {

    private String state = "原始对象";

    private String id;

    private String name;

    private String phone;

    public Originator() {
    }

    // 创建备忘录对象的方法
    public Memento createMemento() {
        return new Memento(id,name,phone);
    }
    // 恢复对象
    public void restoreMemento(Memento m) {
        this.state = m.getState();
        this.id = m.getId();
        this.name = m.getName();
        this.phone = m.getPhone();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Originator{" +
                "state='" + state + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
