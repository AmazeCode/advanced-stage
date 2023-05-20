package com.ac.springboot.design.behavior.observer.observer02.simple;

/**
 * 模拟买房摇号服务
 * @Author: zhangyadong
 * @Date: 2022/12/17 14:59
 */
public class DrawHouseService {

    // 摇号抽签
    public String lots(String uId) {
        if (uId.hashCode() % 2 == 0) {
            return "恭喜ID为：" + uId + "的用户，在本次摇号中中签！";
        }else{
            return "很遗憾ID为：" + uId + "的用户，您本次未中签！";
        }
    }
}
