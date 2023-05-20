package com.ac.springboot.design.structure.proxy.proxy01.impl;

import com.ac.springboot.design.structure.proxy.proxy01.IUserDao;

/**
 * 目标类 
 * @Author: zhangyadong
 * @Date: 2022/12/6 22:35
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存数据！");
    }
}
