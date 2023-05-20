package com.ac.springboot.design.create.prototype.clone.deep;

import com.ac.springboot.design.create.prototype.clone.deep.entity.Student;

import java.io.Serializable;

/**
 * 如果使用序列化必须要实现序列化接口
 * @Author: zhangyadong
 * @Date: 2022/11/28 13:12
 */
public class DeepClone implements Cloneable, Serializable {

    private Student student;

    public DeepClone() {
        System.out.println("具体原型对象创建成功！");
    }

    public void show() {
        System.out.println("嫌疑人姓名：" + student.getName());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    protected DeepClone clone() throws CloneNotSupportedException {
        System.out.println("克隆对象复制成功！");
        return (DeepClone)super.clone();
    }
}
