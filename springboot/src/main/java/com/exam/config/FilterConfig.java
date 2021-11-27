package com.exam.config;

import com.exam.filter.PrivilegeFilter;
import com.exam.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerSessionFilter() {
        FilterRegistrationBean<SessionFilter> bean = new FilterRegistrationBean<>();
        // 控制Filter顺序，值越小优先级越高
        bean.setOrder(1);
        bean.setFilter(new SessionFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Bean
    public FilterRegistrationBean registerPrivilegeFilter() {
        FilterRegistrationBean<PrivilegeFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(2);
        bean.setFilter(new PrivilegeFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

}
