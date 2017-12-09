package com.free.framework.plateform.config.security;

import com.free.framework.core.user.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * spring security配置
 * @author smile
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    UserDetailsService customUserService() {
        return new CustomerUserDetailService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/js/**",
                            "/css/**",
                            "/images/**",
                            "/component/**",
                            "/fonts/**"
                    ).permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/users/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/")
                    .successForwardUrl("")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("resources/static/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }
}
