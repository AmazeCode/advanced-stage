package com.ac.springboot.design.structure.component.component02;


/**
 * 组合模式
 * Entry抽象类（文件夹+文件）
 * @Author: zhangyadong
 * @Date: 2022/12/15 20:05
 */
public abstract class Entry {

    public abstract String getName();// 获取文件名

    public abstract int getSize(); // 获取文件大小

    //添加文件或者文件夹方法
    public abstract Entry add(Entry entry);

    // 显示指定目录下的所有文件的信息
    public abstract void printList(String prefix);

    @Override
    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}
