package com.ac.springboot.design.behavior.memento.memento1;

/**
 * 负责人类-获取和保存备忘录对象
 * @Author: zhangyadong
 * @Date: 2022/12/25 11:46
 */
public class Caretake {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
