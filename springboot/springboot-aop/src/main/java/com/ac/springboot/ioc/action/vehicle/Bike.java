package com.ac.spring.ioc.action.vehicle;

import com.ac.spring.ioc.action.Driveable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *  @Description: 自行车类
 *  @author: zhangyadong
 *  @Date: 2020/8/8 15:33
 *  @version: V1.0
 */
@Slf4j
@Data
public class Bike implements Driveable {

    @Override
    public void driver() {
        log.info("骑车出去浪~");
    }
}
