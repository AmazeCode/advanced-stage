package com.ac.springboot.design.behavior.iterator.iterator2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:55
 */
@SpringBootTest
public class Iterator2Test {

    @Test
    public void iterator2Test() {
        Topic[] topics = new Topic[4];
        topics[0] = new Topic("t1");
        topics[1] = new Topic("t2");
        topics[2] = new Topic("t3");
        topics[3] = new Topic("t4");

        TopicList topicList = new TopicList(topics);

        IteratorIterator<Topic> iterator = topicList.iterator();

        while (iterator.hasNext()) {
            Topic next = iterator.next();
            System.out.println(next.getName());
        }
    }
}
