package com.ac.swagger.knife4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class SpringbootSwaggerKnife4jApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(SpringbootSwaggerKnife4jApplication.class, args);
        log.info("http://{}:8080/doc.html", Inet4Address.getLocalHost().getHostAddress());
    }

}
