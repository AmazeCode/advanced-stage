package com.ac.springboot.design.behavior.command.command1;

import cn.hutool.core.lang.hash.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/25 12:35
 */
public class Order {

    private int diningTable; // 餐桌号码

    private Map<String,Integer> foodMenu = new HashMap<>();// 存储菜单名和份数

    public int getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(int diningTable) {
        this.diningTable = diningTable;
    }

    public Map<String, Integer> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(Map<String, Integer> foodMenu) {
        this.foodMenu = foodMenu;
    }
}
