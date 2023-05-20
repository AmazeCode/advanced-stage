package com.ac.springboot.design.behavior.strategy.strategy5;

import java.util.ArrayList;
import java.util.List;

/**
 * 回执信息生成类（不适用策略模式）
 * @Author: zhangyadong
 * @Date: 2022/12/24 11:01
 */
public class ReceiptBuilder {

    public static List<Receipt> getReceiptLis() {
        // 模拟回执信息
        List<Receipt> list = new ArrayList<>();

        // MT1101、MT2101、MT4101、MT8104
        list.add(new Receipt("MT1101回执信息","MT1101"));
        //list.add(new Receipt("MT2101回执信息","MT2101"));
        //list.add(new Receipt("MT4101回执信息","MT4101"));
        //list.add(new Receipt("MT8104回执信息","MT8104"));
        return list;
    }
}
