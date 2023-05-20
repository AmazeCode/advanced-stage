package com.ac.springboot.design.behavior.memento.memento1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/25 11:47
 */
@SpringBootTest
public class Memento1Test {

    @Test
    public void memento1Test() {
        // 创建发起人对象
        Originator o1 = new Originator();
        o1.setId("1");
        o1.setName("spike");
        o1.setPhone("1234");
        System.out.println("=============="+o1);

        // 创建负责人对象
        Caretake caretake = new Caretake();
        caretake.setMemento(o1.createMemento());

        // 修改
        o1.setName("update");
        System.out.println("=========" + o1);

        // 从负责人对象中获取备忘录，实现恢复操作
        o1.restoreMemento(caretake.getMemento());
        System.out.println("===================" + o1);
    }
}
