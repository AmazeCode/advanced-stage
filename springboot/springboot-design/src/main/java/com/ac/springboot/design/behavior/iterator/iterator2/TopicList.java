package com.ac.springboot.design.behavior.iterator.iterator2;

/**
 * 具体集合类
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:53
 */
public class TopicList implements ListList<Topic> {

    private Topic[] topics;

    public TopicList(Topic[] topics) {
        this.topics = topics;
    }

    @Override
    public IteratorIterator<Topic> iterator() {
        return new TopicIterator(topics);
    }
}
