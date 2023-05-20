package com.ac.springboot.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *  @Description: 测试类
 *  @author: zhangyadong
 *  @Date: 2019/11/21 16:28
 *  @version: V1.0
 */
@SpringBootTest
public class ServletControllerTest {


    private MockMvc mvc;

    @Test
    public void webServiceTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/servlet/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
