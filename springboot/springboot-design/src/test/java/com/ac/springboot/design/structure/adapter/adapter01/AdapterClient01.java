package com.ac.springboot.design.structure.adapter.adapter01;

import com.ac.springboot.design.structure.adapter.adapter01.impl.SDCardImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:30
 */
@SpringBootTest
public class AdapterClient01 {

    @Test
    public void adapterTest01() {
        Computer computer = new Computer();
        SDCard sdCard = new SDCardImpl();
        String read = computer.read(sdCard);
        System.out.println(read);

        System.out.println("============================");
        SDAdapterTF adapterTF = new SDAdapterTF();
        System.out.println(computer.read(adapterTF));
    }
}
