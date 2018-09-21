package com.self.concurrent.config;

import com.self.concurrent.filter.HttpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucifer
 * @do 配置过滤器的bean
 * @date 2018/09/18 17:23
 */
@Configuration
public class FilterRegistConfig {

    /**
     * @do 为过滤器类配置过滤路径,并且配置让其开始生效
     * @return
     */
    @Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpFilter());
        filterRegistrationBean.addUrlPatterns("/threadlocal/*");
        return filterRegistrationBean;
    }

}
