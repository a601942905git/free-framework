package com.free.framework.core.user.service;

import com.free.framework.core.role.controller.param.RoleParam;
import com.free.framework.core.role.entity.Role;
import com.free.framework.core.role.service.RoleService;
import com.free.framework.core.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * com.free.framework.core.user.service.CustomerUserDetailService
 * 自定义用户详情业务
 * @author lipeng
 * @dateTime 2017/12/10 20:13
 */
@Service
@Slf4j
public class CustomerUserDetailService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new InternalAuthenticationServiceException("登录账号为空");
        }

        // 根据登录名称查询用户信息
        Optional<User> optionalUser = userService.getUserByLoginCode(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("账号或密码错误");
        }

        User user = optionalUser.get();

        RoleParam roleParam = new RoleParam();
        roleParam.setPageSize(1000);
        List<Role> roleList = roleService.pageRole(roleParam).getList();

        /**
         * 存放用户拥有的角色编号
         */
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getId())));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLoginCode(),
                user.getLoginPassword(),
                // 是否可用
                true,
                // 是否过期
                true,
                // 证书不过期为true
                true,
                // 账户未锁定为true
                true,
                authorities);
    }
}
