package com.ac.springboot.design.behavior.iterator.iterator2;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:50
 */
public class TopicIterator implements IteratorIterator<Topic> {

    // Topic数组
    private Topic[] topics;

    // 记录存储位置的游标
    private int position;

    public TopicIterator(Topic[] topics) {
        this.topics = topics;
        this.position = 0;
    }

    @Override
    public void reset() {
        position = 0;
    }

    @Override
    public Topic next() {
        return topics[position++];
    }

    @Override
    public Topic currentItem() {
        return topics[position];
    }

    @Override
    public boolean hasNext() {
        if (position >= topics.length) {
            return false;
        }
        return true;
    }
}
