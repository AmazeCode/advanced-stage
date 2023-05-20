package com.ac.springboot.design.create.prototype.clone.shallow;

import com.ac.springboot.design.create.prototype.clone.shallow.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 浅拷贝测试
 * @Author: zhangyadong
 * @Date: 2022/11/28 13:05
 */
@SpringBootTest
public class ConcretePrototypeTest {

    @Test
    public void concretePrototypeTest() throws CloneNotSupportedException {
        ConcretePrototype c1 = new ConcretePrototype();
        ConcretePrototype c2 = c1.clone();

        System.out.println("对象c1和对象c2是同一个对象吗？" + (c1 == c2));
    }

    @Test
    public void shallowCloneTest () throws Exception {
        ConcretePrototype c1 = new ConcretePrototype();
        Person p1 = new Person("小明");
        c1.setPerson(p1);

        // 复制c1
        ConcretePrototype c2 = c1.clone();
        Person p2 = c2.getPerson();
        p2.setName("小王");

        c1.show();
        c2.show();

        System.out.println("对象p1和对象p2是同一个对象吗？" + (p1 == p2));
    }
}
