package com.ac.springboot.design.behavior.visit.visit1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/12/25 10:20
 */
@SpringBootTest
public class Visit1Test {

    @Test
    public void visit1Test() {
        /*Candy candy = new Candy("德芙巧克力", LocalDate.of(2022,1,1),10);
        Visit visit = new DiscountVisit(LocalDate.of(2022, 10, 5));

        visit.visit(candy);*/

        // 将3件商品加入购物车
        /*List<Product> products = Arrays.asList(
                new Candy("德芙巧克力", LocalDate.of(2022,1,1),10),
                new Wine("郎酒",LocalDate.of(2022,10,1),1000),
                new Fruit("草莓",LocalDate.of(2022,10,8),50,1)
        );

       Visit visit = new DiscountVisit(LocalDate.of(2022,10,5));

        for (Product product : products) {
            //visit.visit(); // 会报错
        }*/

        // 模拟添加多个商品
        List<Acceptable> list = Arrays.asList(
                new Candy("德芙巧克力", LocalDate.of(2022,1,1),10),
                new Wine("郎酒",LocalDate.of(2022,10,1),1000),
                new Fruit("草莓",LocalDate.of(2022,10,8),50,1)
        );

        Visit visit = new DiscountVisit(LocalDate.of(2022,10,5));

        for (Acceptable product : list) {
            product.accept(visit);
        }
    }
}
