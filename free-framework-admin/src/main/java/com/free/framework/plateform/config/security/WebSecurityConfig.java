package com.free.framework.plateform.config.security;

import com.free.framework.core.user.service.CustomerUserDetailService;
import com.free.framework.core.user.util.UserUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * com.free.framework.plateform.config.security.WebSecurityConfig
 * spring security配置
 * @author lipeng
 * @dateTime 2017/12/10 20:13
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 注册UserDetailsService 的bean
    @Bean
    UserDetailsService customUserService(){
        return new CustomerUserDetailService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // user Details Service验证
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {

            /**
             * 密码加密
             * @param password
             * @return
             */
            @Override
            public String encode(CharSequence password) {
                return UserUtils.generateEncryptPassword((String) password);
            }

            /**
             * 校验密码
             * @param password          密码
             * @param encodedPassword   加密后的密码
             * @return
             */
            @Override
            public boolean matches(CharSequence password, String encodedPassword) {
                return encodedPassword.equals(UserUtils.generateEncryptPassword((String)password));
            }
        });

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .permitAll() //登录页面用户任意访问
                .and()
                    .logout().permitAll(); //注销行为任意访问


    }
}
