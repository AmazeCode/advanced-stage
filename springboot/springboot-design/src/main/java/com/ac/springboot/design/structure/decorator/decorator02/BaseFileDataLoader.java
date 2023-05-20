package com.ac.springboot.design.structure.decorator.decorator02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 具体组件：抽象文件读取接口的实现
 * @Author: zhangyadong
 * @Date: 2022/12/14 22:18
 */
public class BaseFileDataLoader implements DataLoader{

    private String filePath;

    public BaseFileDataLoader(String filePath) {
        this.filePath = filePath;
    }

    // 读
    @Override
    public String read() {
        try {
            String s = FileUtils.readFileToString(new File(filePath), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 写
    @Override
    public void write(String data) {
        try {
            FileUtils.writeStringToFile(new File(filePath),data,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
