package com.ac.springboot.design.structure.proxy.proxy01.impl;

import com.ac.springboot.design.structure.proxy.proxy01.IUserDao;

/**
 * 代理类
 * @Author: zhangyadong
 * @Date: 2022/12/6 22:36
 */
public class UserDaoProxy implements IUserDao {

    // 需要引入目标类
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务");// 扩展额外功能
        target.save();
        System.out.println("提交事务");
    }
}
