package com.ac.springboot.design.behavior.chain.unchain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟审核服务
 * @Author: zhangyadong
 * @Date: 2022/12/24 14:42
 */
public class AuthService {

    // 审核信息容器 key:审批人Id+审批单Id， value：审批时间
    private static Map<String, Date> authMap = new HashMap<>();

    /**
     * @description: 审核方法
     * @param: uId
     * @param: orderId
     * @return: void
     * @author: zhangyadong
     * @date: 2022/12/24 14:44
     */
    public static void auth(String uId, String orderId) {
        System.out.println("进入审批，审批人ID" + uId);
        authMap.put(uId.concat(orderId),new Date());
    }

    /**
     * @description: 查询审核结果
     * @param: uId
     * @param: orderId
     * @return: java.util.Date
     * @author: zhangyadong
     * @date: 2022/12/24 14:46
     */
    public static Date queryAuthInfo(String uId, String orderId) {
        return authMap.get(uId.concat(orderId));
    }
}
