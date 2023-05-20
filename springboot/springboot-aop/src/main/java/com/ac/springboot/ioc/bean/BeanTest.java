package com.ac.springboot.ioc.bean;

import com.ac.spring.ioc.action.Driveable;
import com.ac.spring.ioc.action.vehicle.Bike;
import com.ac.spring.ioc.action.vehicle.Car;
import com.ac.spring.ioc.model.Person;
import org.junit.Before;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

/**
 * @Description: B测试类
 * @author: zhangyadong
 * @Date: 2020/8/8 17:07
 * @version: V1.0
 */
public class BeanTest {

    // Bean 容器
    private Map<String, Object> container = new HashMap<>();

    /**
     * 模拟 Spring 容器初始化
     */
    @Before
    public void init() {
        Driveable bike = new Bike();
        container.put("bike", bike);

        Driveable car = new Car();
        container.put("car", car);
    }

    /**
     * 获取 Bean
     *
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return container.get(name);
    }

    @Test
    public void test() {
        Driveable bike = (Driveable) container.get("bike");
        Person xiaoming = new Person(bike);
        xiaoming.hangOut();

        Driveable car = (Driveable) container.get("car");
        xiaoming = new Person(car);
        xiaoming.hangOut();
    }
}
