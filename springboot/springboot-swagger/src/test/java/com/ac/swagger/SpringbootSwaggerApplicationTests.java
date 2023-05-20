package com.ac.swagger;

import com.ac.swagger.ao.PersonAo;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SpringbootSwaggerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	private MockMvc mvc;

	/**
	 * ˵��:��������ʵ�壺����Ҫ���.content(JSONObject.toJSONString(personAo)),���ǵ����������ע��ȥ��.content(JSONObject.toJSONString(personAo))
	 *
	 */
	@Test
	public void hello() throws Exception {
		PersonAo personAo = new PersonAo();
		personAo.setUserName("����");
		MvcResult result = mvc.perform(post("/").content(JSONObject.toJSONString(personAo))
				/*.param("name", "IMOOC")*/
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		System.out.println("==="+result.getResponse().getContentAsString()+"===");
	}
}
