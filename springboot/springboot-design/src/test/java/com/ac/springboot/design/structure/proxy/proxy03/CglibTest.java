package com.ac.springboot.design.structure.proxy.proxy03;

import com.ac.springboot.design.structure.proxy.proxy03.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/9 11:54
 */
@SpringBootTest
public class CglibTest {

    @Test
    public void cglibTest() {

        // 目标对象
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.getClass());

        // 代理对象
        UserServiceImpl proxy = (UserServiceImpl)new UserLogProxy().getLogProxy(userService);
        System.out.println(proxy.getClass());

        List<User> userList = proxy.findUserList();
        System.out.println("用户信息：" + userList);
    }
}
