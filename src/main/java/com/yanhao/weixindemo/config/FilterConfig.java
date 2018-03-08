package com.yanhao.weixindemo.config;

import com.yanhao.weixindemo.filter.AauthorizationFilter;
import com.yanhao.weixindemo.filter.BtokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean authorFilter() {
        FilterRegistrationBean author = new FilterRegistrationBean();
        author.addUrlPatterns("/user/*");
        author.setFilter(new AauthorizationFilter());
        return author;
    }
    @Bean
    public FilterRegistrationBean tokenFilter() {
        FilterRegistrationBean token = new FilterRegistrationBean();
        token.addUrlPatterns("/user/*");
        token.setFilter(new BtokenFilter());
        return token;
    }
}
