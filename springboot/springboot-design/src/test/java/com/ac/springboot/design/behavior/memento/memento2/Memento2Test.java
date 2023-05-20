package com.ac.springboot.design.behavior.memento.memento2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/25 12:13
 */
@SpringBootTest
public class Memento2Test {

    @Test
    public void memento2Test() throws InterruptedException {
        // 创建玩家类，设置初始金币
        Player player = new Player(100);

        // 创建备忘录对象
        Memento memento = player.createMemento();

        for (int i = 0; i < 100; i++) {
            // 显示扔骰子的次数
            System.out.println("第" + i + "次投掷！");

            // 显示当前玩家状态
            System.out.println("当前状态：" + player);

            // 开启游戏
            player.yacht();
            System.out.println("玩家所持有的金币：" + player.getMoney());

            // 复活操作
            if (player.getMoney() > memento.getMoney()) {
                System.out.println("赚到金币，保存当前状态，继续游戏！");
                memento = player.createMemento(); // 更新快照
            }else if (player.getMoney() < memento.getMoney() / 2){
                System.out.println("所持的金币不多，将游戏恢复到初始状态！");
                player.restoreMemento(memento);
            }

            Thread.sleep(1000);
        }
    }
}
