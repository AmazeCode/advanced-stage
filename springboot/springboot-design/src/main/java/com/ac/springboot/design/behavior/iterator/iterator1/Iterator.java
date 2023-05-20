package com.ac.springboot.design.behavior.iterator.iterator1;

/**
 * 迭代器接口
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:34
 */
public interface Iterator<E> {

    // 判断集合中是否有下一个元素
    boolean hasNext();

    // 将游标后移一位
    void next();

    // 返回当前游标指定的元素
    E currentItem();
}
