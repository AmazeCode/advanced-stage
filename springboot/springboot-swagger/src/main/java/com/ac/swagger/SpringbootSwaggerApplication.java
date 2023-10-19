package com.ac.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class SpringbootSwaggerApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(SpringbootSwaggerApplication.class, args);
		log.info("接口文档地址:http://{}:8080/swagger-ui.html", Inet4Address.getLocalHost().getHostAddress());
	}

}
