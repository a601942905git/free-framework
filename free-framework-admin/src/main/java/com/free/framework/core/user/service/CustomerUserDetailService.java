package com.free.framework.core.user.service;

import com.free.framework.core.role.entity.Role;
import com.free.framework.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * com.free.framework.core.user.service.CustomerUserDetailService
 * 自定义用户详情业务
 * @author lipeng
 * @dateTime 2017/12/10 20:13
 */
@Service
public class CustomerUserDetailService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByLoginCode(s);
        if (null == user) {
            String message = String.format("userName:%s,not found", s);
            throw new UsernameNotFoundException(message);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 用于添加用户的权限,只要把用户权限添加到authorities 就万事大吉。
        for(Role role:user.getRoleList()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginCode(),
                user.getLoginPassword(), authorities);
    }
}
