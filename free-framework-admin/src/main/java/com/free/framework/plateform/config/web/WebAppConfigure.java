package com.free.framework.plateform.config.web;


import com.free.framework.core.login.controller.LoginControllerMappingUrl;
import com.free.framework.plateform.csrf.interceptor.CsrfTokenInterceptor;
import com.free.framework.core.file.FileControllerMappingUrl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * com.free.framework.plateform.config.web.WebAppConfigure
 * web mvc配置
 *
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Configuration
public class WebAppConfigure extends WebMvcConfigurerAdapter {

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CsrfTokenInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加跳转视图控制
     * 以前跳转一个界面,需要在controller中写一个方法,然后进行跳转,现在可以再此处进行配置
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 登录页面
        registry.addViewController(LoginControllerMappingUrl.LOGIN_CONTROLLER)
                .setViewName(LoginControllerMappingUrl.LOGIN_RETURN_PAGE1);

        // 文件上传页面
        registry.addViewController(FileControllerMappingUrl.FILE_CONTROLLER + FileControllerMappingUrl.FILE)
                .setViewName(FileControllerMappingUrl.FILE_RETURN_PAGE);
    }
}
