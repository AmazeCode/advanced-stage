package com.ac.springboot.design.behavior.chain.chain2;

import com.ac.springboot.design.behavior.chain.unchain.AuthInfo;
import com.ac.springboot.design.behavior.chain.unchain.AuthService;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/24 15:29
 */
public class Level3AuthLink extends AuthLink {

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);

        if (null == date) {
            return new AuthInfo("0001","单号："+orderId,"状态：待三级审核人审批",levelUserName);
        }
        AuthLink next = super.getNext();
        if (next == null) {
            return new AuthInfo("0001","单号："+orderId,"状态：三级审批完成","审批人：",levelUserName);
        }

        return next.doAuth(uId,orderId,authDate);
    }
}