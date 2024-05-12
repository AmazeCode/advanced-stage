package com.ac.swagger.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @Description: Swagger配置
 * @author: zhangyadong
 * @Date: 2020/7/22 0022 下午 8:57
 * @version: V1.0
 */
@Configuration
@EnableSwagger2 // springfox-swagger2 比当前版本高时该注解废弃，使用@EnableSwagger2WebMvc
public class Swagger2Config {

    @Resource
    private Environment environment;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("com.ac.swagger.controller"))
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
    private ApiInfo apiInfo(){

        String swaggerurl = null;
        try {
            swaggerurl = String.format("http://%s:%s/swagger-ui.html", Inet4Address.getLocalHost().getHostAddress(),environment.getProperty("server.port"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new ApiInfoBuilder()
                .title("Spring Boot实战")
                .description(swaggerurl)
                .version("1.0").build();
    }

    /*
        整合步骤:
        1、引入依赖
        2、添加配置类
        3、设置yml配置文件
     */
}
