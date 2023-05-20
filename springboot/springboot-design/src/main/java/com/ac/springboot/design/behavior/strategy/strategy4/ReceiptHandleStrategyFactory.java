package com.ac.springboot.design.behavior.strategy.strategy4;

import com.ac.springboot.design.behavior.strategy.strategy4.Receipt;
import com.ac.springboot.design.behavior.strategy.strategy4.MT1101ReceiptHandleStrategy;
import com.ac.springboot.design.behavior.strategy.strategy4.MT2101ReceiptHandleStrategy;
import com.ac.springboot.design.behavior.strategy.strategy4.ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂类
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:25
 */
public class ReceiptHandleStrategyFactory {

    public ReceiptHandleStrategyFactory() {
    }

    // 使用Map结合存储策略信息，彻底消除if。。。。else
    private static Map<String, ReceiptHandleStrategy> strategyMap;

    // 初始化具体策略，保存到map集合
    public static void init() {
        strategyMap = new HashMap<>();
        strategyMap.put("MT1101",new MT1101ReceiptHandleStrategy());
        strategyMap.put("MT2101",new MT2101ReceiptHandleStrategy());
    }

    // 根据回执的类型，获取对应的策略对象
    public static ReceiptHandleStrategy getStrategy(String receiptType) {
        return strategyMap.get(receiptType);
    }
}
