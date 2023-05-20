package com.ac.springboot.design.create.prototype.clone.shallow;

import com.ac.springboot.design.create.prototype.clone.shallow.entity.Person;

/**
 * 具体原型类(浅克隆)
 *  实现Cloneable标识接口，表示当前类对象可复制
 * @Author: zhangyadong
 * @Date: 2022/11/28 13:02
 */
public class ConcretePrototype implements Cloneable{

    private Person person;

    public ConcretePrototype() {
        System.out.println("具体原型对象创建成功！");
    }

    public void show() {
        System.out.println("嫌疑人姓名：" + person.getName());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    protected ConcretePrototype clone() throws CloneNotSupportedException {
        System.out.println("克隆对象复制成功！");
        return (ConcretePrototype)super.clone();
    }
}
