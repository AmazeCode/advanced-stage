package com.ac.springboot.design.structure.component.component01;

/**
 * 组合模式
 * 抽象根节点角色
 *      对客户端而言，只需要针对抽象编程，无需关心具体子类是树枝节点还是叶子节点
 * @Author: zhangyadong
 * @Date: 2022/12/15 19:51
 */
public abstract class Component {

    public abstract void add(Component c);// 增加节点
    public abstract void remove(Component c);//删除节点
    public abstract Component getChild(int i);// 获取节点
    public abstract void operation();//抽象业务方法
}
