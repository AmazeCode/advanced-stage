package com.ac.springboot.design.behavior.chain.unchain;

import com.ac.springboot.design.create.factory.factory01.service.IFreeGoods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 审核申请接口
 * @Author: zhangyadong
 * @Date: 2022/12/24 14:47
 */
public class AuthController {

    /**
     * @description: 审核方法
     * @param: name    申请人
     * @param: orderId 申请单id
     * @param: authDate 申请时间
     * @return: com.ac.springboot.design.behavior.chain.unchain.AuthInfo
     * @author: zhangyadong
     * @date: 2022/12/24 14:48
     */
    public AuthInfo doAuth(String name, String orderId, Date authDate) throws ParseException {
        // 三级审核人
        Date date = null;

        // 查询是否存在审核信息,虚拟三级审核人id:1000013
        date = AuthService.queryAuthInfo("1000013", orderId);
        if (date == null) {
            return new AuthInfo("0001","单号：" + orderId, "状态：等待三级审批负责人进行审批");
        }

        // 查询是否存在审核信息,虚拟二级审核人id:1000012 二级审核人审核申请单的时间范围为11-01~11-10
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (authDate.after(sdf.parse("2023-10-31 00:00:00")) && authDate.before(sdf.parse("2023-11-11 00:00:00"))) {
            // 条件成立，查询二级审核信息
            date = AuthService.queryAuthInfo("1000012", orderId);
            if (date == null) {
                return new AuthInfo("0002","单号：" + orderId, "状态：等待二级审批负责人进行审批");
            }
        }

        // 查询是否存在审核信息,虚拟一级审核人id:1000011 二级审核人审核申请单的时间范围为11-10~11-31
        if (authDate.after(sdf.parse("2023-11-10 00:00:00")) && authDate.before(sdf.parse("2023-11-31 00:00:00"))) {
            // 条件成立，查询二级审核信息
            date = AuthService.queryAuthInfo("1000012", orderId);
            if (date == null) {
                return new AuthInfo("0001","单号：" + orderId, "状态：等待一级审批负责人进行审批");
            }
        }

        return new AuthInfo("0001","单号：" + orderId, "申请人：" + name, "状态：审批完成");
    }
}
