package com.ac.springboot.design.structure.component.component01;

import java.util.ArrayList;

/**
 * 树枝节点
 *      树枝节点类是一个容器对象，它既可以包含树枝节点也可以包含叶子节点
 * @Author: zhangyadong
 * @Date: 2022/12/15 19:56
 */
public class Composite extends Component {

    // 定义集合属性，保存子节点的数据
    private ArrayList<Component> list = new ArrayList<>();


    @Override
    public void add(Component c) {
        list.add(c);
    }

    @Override
    public void remove(Component c) {
        list.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return list.get(i);
    }

    // 具体业务方法
    @Override
    public void operation() {
        // 在循环中，递归调用其它节点中的operation()方法
        for (Component component : list) {
            component.operation();
        }
    }
}
