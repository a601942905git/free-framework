/*
package com.free.framework.plateform.config.security;

import com.free.framework.util.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

*/
/**
 * com.free.framework.plateform.config.security.CustomerAccessDecisionManager
 * 决策类,当用户请求一个url地址,根据url地址查询可以访问该url地址的角色
 * 然后判断用户时候拥有该角色,如果拥有该角色,那么就放行
 * @author lipeng
 * @dateTime 2017/12/11 14:15
 *//*

@Component
public class CustomerAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(CollectionUtils.isEmpty(configAttributes)) {
            return;
        }

        String needRole;
        for (ConfigAttribute c : configAttributes) {
            // 当前访问url需要的角色
            needRole = c.getAttribute();
            // 遍历当前用户拥有的角色
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
*/
