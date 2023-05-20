package com.ac.springboot.design.structure.component.component02;

import java.util.ArrayList;

/**
 * Directory 容器对象，表示文件夹
 * @Author: zhangyadong
 * @Date: 2022/12/15 20:14
 */
public class Directory extends Entry {

    //文件的名字
    private String name;

    // 文件夹和文件的集合
    private ArrayList<Entry> directory = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 获取文件大小
     *  1、如果Entry对象是file类型，则调用getSize方法获取文件大小
     *  2、如果Entry对象是Directory类型，会继续调用子文件夹下的getSize（）方法递归调用
     * @param:
     * @return: int
     * @author: zhangyadong
     * @date: 2022/12/15 20:16
     */
    @Override
    public int getSize() {
        int size = 0;

        // 遍历获取文件大小
        for (Entry entry : directory) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) {
        directory.add(entry);
        return this;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        for (Entry entry : directory) {
            entry.printList(prefix + "/" + name);
        }
    }
}
