package com.mybatis.generator.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatis.generator.annotation.NotControllerResponseAdvice;
import com.mybatis.generator.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 统一返回处理Advice
 * @author: AmazeCode
 * @date: 2024/3/24 21:09
 */
@RestControllerAdvice(basePackages = "com.mybatis.generator.controller")// 如果引入swagger或knife4j的文档生成组件，这里需要仅扫描自己的项目包，否则文档无法正常生成
public class ResponseAdvice implements ResponseBodyAdvice<Object> {


    /*
    supports：是否支持给定的控制器方法返回类型和选定的HttpMessageConverter类型；
    若不支持 则就不会对数据进行做统一处理，
    就像上面代码，若加了@NotControllerResponseAdvice注解，
    则不会进行拦截(@NotControllerResponseAdvice是自己自定义的一个注解)
    参数：returnType：返回类型； converterType：选择的转换器类型
    返回：若返回结果为true,则调用beforeBodyWrite
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        List<Boolean> judgeResultList = Arrays.asList(
                // 判断响应的方法上是否包含 NotControllerResponseAdvice 注解
                returnType.hasMethodAnnotation(NotControllerResponseAdvice.class)
        );
        // 若包含其中一项,则不进行封装
        return !judgeResultList.contains(true);
    }


    /*
        beforeBodyWrite：在选择HttpMessageConverter之后以及在调用其write方法之前调用。
        参数：
            body：你传入的数据；
            returnType：controller层方法返回的类型；
            selectedContentType ：通过内容协商选择的内容类型；
            selectedConverterType：选择要写入响应的转换器类型；
            request/response:当前请求和响应
        返回：传入的数据或修改的(可能是新的)实例
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof Result) {
            return body;
        }
        // 如果返回值是String类型，那就手动把Result对象转换成JSON字符串
        if (body instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在Result里后转换为JSON串返回
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(body);
    }
}