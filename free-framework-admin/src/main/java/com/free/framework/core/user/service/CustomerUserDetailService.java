package com.free.framework.core.user.service;

import com.free.framework.core.resource.controller.param.ResourceParam;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.service.ResourceService;
import com.free.framework.core.user.entity.User;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("登录账号为空");
        }

        User user = userService.getUserByLoginCode(username).orElseThrow(() -> new UsernameNotFoundException("登录账号不存在"));

        List<Resource> resources = resourceService.pageResource(new ResourceParam()).getList();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 用于添加用户的权限,只要把用户权限添加到authorities 就万事大吉。
        for(Resource resource : resources) {
            authorities.add(new SimpleGrantedAuthority(resource.getName()));
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
