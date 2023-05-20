package com.ac.springboot.design.behavior.iterator.iterator1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 具体的迭代器
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:36
 */
public class ConcreteIterator<E> implements Iterator<E>{

    private int cursor;// 游标

    private ArrayList<E> arrayList;// 容器

    public ConcreteIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return cursor != arrayList.size();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(cursor);
    }
}
