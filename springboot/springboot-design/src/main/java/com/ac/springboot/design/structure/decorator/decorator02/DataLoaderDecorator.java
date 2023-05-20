package com.ac.springboot.design.structure.decorator.decorator02;

/**
 * 抽象装饰者类
 * @Author: zhangyadong
 * @Date: 2022/12/14 22:29
 */
public class DataLoaderDecorator implements DataLoader{

    // 持有抽象构件的引用
    private DataLoader dataLoader;

    public DataLoaderDecorator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public String read() {
        return dataLoader.read();
    }

    @Override
    public void write(String data) {
        dataLoader.write(data);
    }
}
