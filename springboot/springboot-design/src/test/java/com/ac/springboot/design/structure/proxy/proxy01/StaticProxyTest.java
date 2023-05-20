package com.ac.springboot.design.structure.proxy.proxy01;

import com.ac.springboot.design.structure.proxy.proxy01.impl.UserDaoImpl;
import com.ac.springboot.design.structure.proxy.proxy01.impl.UserDaoProxy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/6 22:43
 */
@SpringBootTest
public class StaticProxyTest {

    @Test
    public void proxyTest() {

        // 目标类
        IUserDao iUserDao = new UserDaoImpl();
        // 代理对象
        UserDaoProxy proxy = new UserDaoProxy(iUserDao);
        proxy.save();
    }
}
