package com.ac.springboot.design.structure.decorator.decorator02;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 具体装饰者
 * @Author: zhangyadong
 * @Date: 2022/12/14 22:32
 */
public class EncryptionDataDecorator extends DataLoaderDecorator{

    public EncryptionDataDecorator(DataLoader dataLoader) {
        super(dataLoader);
    }

    @Override
    public String read() {
        // 进行装饰扩展
        return decode(super.read());
    }

    @Override
    public void write(String data) {
        // 进行装饰扩展
        super.write(encode(data));
    }

    // 加密操作
    public String encode(String data) {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] bytes = data.getBytes("utf-8");
            String result = encoder.encodeToString(bytes);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 解密操作
    public String decode(String data) {

        try {
            Base64.Decoder decoder = Base64.getDecoder();
            String result = new String(decoder.decode(data),"utf-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
