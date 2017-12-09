package com.free.framework.core.user.service;

import com.free.framework.core.user.entity.SecurityUser;
import com.free.framework.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author smile
 */
@Component
public class CustomerUserDetailService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByLoginCode(s);
        SecurityUser securityUser = new SecurityUser(user);
        if (null == securityUser) {
            String message = String.format("userName:%s,not found", s);
            throw new UsernameNotFoundException(message);
        }
        return securityUser;
    }
}
