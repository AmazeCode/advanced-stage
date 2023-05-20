package com.ac.springboot.design.structure.component.component02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 20:22
 */
@SpringBootTest
public class Component02Test {

    @Test
    public void component02Test() {
        // 创建根节点
        Directory rootDir = new Directory("root");

        // 创建树枝节点
        Directory binDir = new Directory("bin");
        // 向bin目录添加叶子节点
        binDir.add(new File("vi",10000));
        binDir.add(new File("test",20000));


        Directory tmpDir = new Directory("tmp");

        Directory usrDir = new Directory("usr");
        Directory mysqlDir = new Directory("mysql");
        mysqlDir.add(new File("my.cnf",30));
        mysqlDir.add(new File("test.db",25000));
        usrDir.add(mysqlDir);

        // 将所有子文件夹封装到根节点
        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);

        rootDir.printList("");
    }
}
