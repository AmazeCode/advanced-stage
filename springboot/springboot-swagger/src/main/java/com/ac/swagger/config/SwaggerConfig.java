package com.ac.swagger.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swagger配置
 * @author: zhangyadong
 * @Date: 2020/7/22 0022 下午 8:57
 * @version: V1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @Description 接口详细说明
     * @params []
     * @return springfox.documentation.service.ApiInfo
     * @author zhangyadong
     * @date 2020/7/22 0022 下午 9:00
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Boot实战")
                .description("Spring Boot实战的RESTFul接口文档说明")
                .version("1.0").build();
    }
}
