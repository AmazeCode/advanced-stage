package com.ac.springboot.design.create.factory.factory01.service;

import com.ac.springboot.design.create.factory.factory01.entity.AwardInfo;
import com.ac.springboot.design.create.factory.factory01.entity.ResponseResult;

/**
 * 免费商品发放接口
 * @Author: zhangyadong
 * @Date: 2022/11/25 18:30
 */
public interface IFreeGoods {

    ResponseResult sendFreeGoods(AwardInfo awardInfo);
}
