package com.ac.swagger.config;

import com.ac.swagger.interceptor.LogInterceptor;
import com.ac.swagger.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  @Description: 自定义web配置
 *  @author: zhangyadong
 *  @Date: 2020/7/25 21:47
 *  @version: V1.0
 */
@Configuration //声明为配置类
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    /**
     * 时间过滤器
     */
    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * @Description 添加自定义拦截器
     * @params [registry]
     * @return void
     * @author zhangyadong
     * @date 2020/7/25 21:49
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加多个过滤器，分现后
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(timeInterceptor);//添加时间过滤器
    }
}
