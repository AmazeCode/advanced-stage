package com.ac.springboot.design.create.prototype.clone.deep;

import com.ac.springboot.design.create.prototype.clone.deep.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 深克隆测试
 * @Author: zhangyadong
 * @Date: 2022/11/28 13:31
 */
@SpringBootTest
public class DeepCloneTest {

    @Test
    public void deepCloneTest () throws Exception {
        DeepClone c1 = new DeepClone();
        Student p1 = new Student("小明");
        c1.setStudent(p1);

        // 创建对象序列化输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.txt"));

        // 将对象写入文件
        oos.writeObject(c1);
        oos.close();


        // 创建对象序列化输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.txt"));
        // 读取对象
        DeepClone c2  = (DeepClone)ois.readObject();

        Student p2 = c2.getStudent();
        p2.setName("小王");

        c1.show();
        c2.show();

        System.out.println("对象p1和对象p2是同一个对象吗？" + (p1 == p2));
    }
}
