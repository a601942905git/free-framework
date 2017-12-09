package com.free.framework.core.user.entity;

import com.free.framework.core.role.entity.Role;
import com.free.framework.util.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author smile
 */
public class SecurityUser extends User implements UserDetails{

    public SecurityUser(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setAge(user.getAge());
        this.setFace(user.getFace());
        this.setSex(user.getSex());
        this.setType(user.getType());
        this.setMobile(user.getMobile());
        this.setLoginCode(user.getLoginCode());
        this.setLoginPassword(user.getLoginPassword());
        this.setRoleList(user.getRoleList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roleList = this.getRoleList();

        if(CollectionUtils.isEmpty(roleList)) {
            return authorities;
        }

        for (Role role : roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getLoginPassword();
    }

    @Override
    public String getUsername() {
        return super.getLoginCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
