package com.ac.springboot.design.structure.component.component01;

/**
 * 叶子节点
 *      叶子节点中不能包含子节点
 * @Author: zhangyadong
 * @Date: 2022/12/15 20:02
 */
public class Leaf extends Component {

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        // 叶子节点中的具体方法
    }
}
