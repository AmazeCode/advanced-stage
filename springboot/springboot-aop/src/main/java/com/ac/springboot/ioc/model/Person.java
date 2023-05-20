package com.ac.spring.ioc.model;

import com.ac.spring.ioc.action.Driveable;
import lombok.AllArgsConstructor;

/**
 *  @Description: 人实体
 *  @author: zhangyadong
 *  @Date: 2020/8/8 15:22
 *  @version: V1.0
 */
@AllArgsConstructor
public class Person {

    private Driveable driveable;

    /**
     * @Description 工具方法
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2020/8/8 16:46
     */
    public void hangOut() {
        driveable.driver();
    }
}
