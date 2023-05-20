package com.ac.springboot.design.behavior.iterator.iterator2;

/**
 * 抽象迭代器
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:45
 */
public interface IteratorIterator<E> {

    void reset(); // 重置为第一个元素

    E next(); // 获取下一个元素

    E currentItem();// 检索当前元素

    boolean hasNext();// 判断集合是否还有下一个元素
}
