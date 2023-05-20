package com.ac.springboot.design.structure.decorator.decorator02;

/**
 * 装饰器模式
 * 抽象的文件读取接口
 * @Author: zhangyadong
 * @Date: 2022/12/14 22:16
 */
public interface DataLoader {

    String read();

    void write(String data);
}
