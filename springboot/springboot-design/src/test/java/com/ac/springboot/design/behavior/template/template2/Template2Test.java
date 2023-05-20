package com.ac.springboot.design.behavior.template.template2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/23 22:03
 */
@SpringBootTest
public class Template2Test {

    @Test
    public void loanTemplateTest() {

        Account loanSevenDays = new LoanSevenDays();
        loanSevenDays.handle("tom","123456");

        System.out.println("==============================");

        Account loanOneMonth = new LoanOneMonth();
        loanOneMonth.handle("tom","123456");
    }
}
