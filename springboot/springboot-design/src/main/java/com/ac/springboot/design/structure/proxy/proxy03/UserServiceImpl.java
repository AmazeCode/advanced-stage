package com.ac.springboot.design.structure.proxy.proxy03;

import com.ac.springboot.design.structure.proxy.proxy03.entity.User;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/9 11:30
 */
public class UserServiceImpl {

    // 查询功能
    public List<User> findUserList () {

        return Collections.singletonList(new User("Tom",23));
    }
}
