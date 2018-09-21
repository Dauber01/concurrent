package com.self.concurrent.config;

import com.self.concurrent.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Lucifer
 * @do 拦截器的配置bean
 * @date 2018/09/18 17:36
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    /**
     * @do 注册自定义拦截器HttpInterceptor,并设置拦截器的路径为"/**"
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }

}
