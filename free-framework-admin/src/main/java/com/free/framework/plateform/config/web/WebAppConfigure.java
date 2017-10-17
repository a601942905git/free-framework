package com.free.framework.plateform.config.web;


import com.free.framework.plateform.csrf.interceptor.CsrfTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web配置
 * @author lipeng
 */
@Configuration
public class WebAppConfigure extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CsrfTokenInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
