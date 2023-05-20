package com.ac.springboot.design.structure.adapter.adapter02;

import com.ac.springboot.design.structure.adapter.adapter01.Computer;
import com.ac.springboot.design.structure.adapter.adapter01.SDAdapterTF;
import com.ac.springboot.design.structure.adapter.adapter01.SDCard;
import com.ac.springboot.design.structure.adapter.adapter01.TFCard;
import com.ac.springboot.design.structure.adapter.adapter01.impl.SDCardImpl;
import com.ac.springboot.design.structure.adapter.adapter01.impl.TFCardImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/15 11:30
 */
@SpringBootTest
public class AdapterClient02 {

    @Test
    public void adapterTest02() {
        Computer computer = new Computer();
        SDCard sdCard = new SDCardImpl();
        String read = computer.read(sdCard);
        System.out.println(read);

        System.out.println("============================");
        TFCard tfCard = new TFCardImpl();
        SDAdapterTF02 adapterTF = new SDAdapterTF02(tfCard);
        System.out.println(computer.read(adapterTF));
    }
}
