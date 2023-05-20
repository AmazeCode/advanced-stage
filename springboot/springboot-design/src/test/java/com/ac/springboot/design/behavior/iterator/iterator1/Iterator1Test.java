package com.ac.springboot.design.behavior.iterator.iterator1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 21:40
 */
@SpringBootTest
public class Iterator1Test {

    @Test
    public void iterator1Test() {
        ArrayList<String> names = new ArrayList<>();
        names.add("lisi");
        names.add("zhangsan");
        names.add("wangwu");

        Iterator<String> iterator = new ConcreteIterator<>(names);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
