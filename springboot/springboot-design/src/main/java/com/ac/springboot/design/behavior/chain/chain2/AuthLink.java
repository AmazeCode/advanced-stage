package com.ac.springboot.design.behavior.chain.chain2;

import com.ac.springboot.design.behavior.chain.unchain.AuthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 抽象审核链类
 * @Author: zhangyadong
 * @Date: 2022/12/24 15:20
 */
public abstract class AuthLink {

    protected Logger logger = LoggerFactory.getLogger(AuthLink.class);

    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected String levelUserId;// 审核人id

    protected String levelUserName;// 审核人姓名

    protected AuthLink next; // 表示持有下一个处理对象的引用

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    // 获取下一个处理类
    public AuthLink getNext() {
        return next;
    }

    // 向责任链中添加处理器
    public AuthLink appendNext(AuthLink next){
        this.next = next;
        return this;
    }

    // 抽象审核方法
    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);
}
