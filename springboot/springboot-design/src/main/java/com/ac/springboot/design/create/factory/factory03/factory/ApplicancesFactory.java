package com.ac.springboot.design.create.factory.factory03.factory;

import com.ac.springboot.design.create.factory.factory03.product.AbstractFreezer;
import com.ac.springboot.design.create.factory.factory03.product.AbstractTV;

/**
 * 抽象工厂：在一个抽象工厂中可以声明多个工厂方法，用于创建不同类型的产品
 * 产品等级结构：其实就是继承结构，比如电视（抽象），下面实现有海尔电视，海信电视，小米电视，这个关系就是产品结构
 * 产品族：指的是由同一个工厂生产的位于不同产品等级中的一组产品，比如 海尔电视、海尔冰箱这两个组合在一起就叫做产品族
 * 抽象工厂概述：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类，抽象工厂模式为创建一组对象提供了解决方案
 * 解释说就是：抽象工厂就是通过某些相关产品抽象工厂生产具体产品的过程，抽象工厂是生产产品族的工厂
 * @Author: zhangyadong
 * @Date: 2022/11/26 20:56
 */
public interface ApplicancesFactory {

    /**
     * @description: 生产电视机
     * @param:
     * @return: com.ac.springboot.design.create.factory.factory03.product.AbstractTV
     * @author: zhangyadong
     * @date: 2022/11/26 20:58
     */
    AbstractTV createTV();

    /**
     * @description: 生产电冰箱
     * @param:
     * @return: com.ac.springboot.design.create.factory.factory03.product.AbstractFreezer
     * @author: zhangyadong
     * @date: 2022/11/26 20:58
     */
    AbstractFreezer createFreezer();
}
