package com.ac.springboot.design.structure.component.component02;

/**
 * File类，表示文件
 * @Author: zhangyadong
 * @Date: 2022/12/15 20:10
 */
public class File extends Entry {

    private String name;//文件名
    private int size;// 文件大小

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Entry add(Entry entry) {
        return null;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
