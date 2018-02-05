/*
package com.free.framework.plateform.config.security;

import com.free.framework.plateform.constant.SystemConstants;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * com.free.framework.plateform.config.security.CustomerInvocationSecurityMetadataSourceService
 *
 * @author lipeng
 * @dateTime 2017/12/11 14:20
 *//*

@Component
public class CustomerInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    public static final String QUESTION_MARK = "?";

    */
/**
     * 存放资源、角色
     * key为资源的url,value为持有当前url的角色列表
     *//*

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    */
/**
     * 加载所有的角色资源
     * 加载所有的资源,以及资源对应的角色
     *
     *//*

    public void  loadResourceDefine(){
        resourceMap = new HashMap<>(SystemConstants.DEFAULT_MAP_CAPACITY);
        resourceMap.put("/organizations/", new ArrayList<ConfigAttribute>(){{
            add(new SecurityConfig(String.valueOf(10000)));}});
        resourceMap.put("/users/", new ArrayList<ConfigAttribute>(){{
            add(new SecurityConfig(String.valueOf(200000)));}});
        resourceMap.put("/roles/", new ArrayList<ConfigAttribute>(){{
            add(new SecurityConfig(String.valueOf(300000)));}});
        resourceMap.put("/resources/", new ArrayList<ConfigAttribute>(){{
            add(new SecurityConfig(String.valueOf(400000)));}});
        resourceMap.put("/users/", new ArrayList<ConfigAttribute>(){{
            add(new SecurityConfig(String.valueOf(500000)));}});
        resourceMap.put("/users/", new ArrayList<ConfigAttribute>(){{
            add(new SecurityConfig(String.valueOf(600000)));}});
    }

    */
/**
     * 返回可以访问当前url的角色列表
     * @param object
     * @return
     * @throws IllegalArgumentException
     *//*

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 加载所有的角色资源信息
        loadResourceDefine();
        // 当前请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 判断请求是否有明文参数，如果是，截取参数前的url，不是则直接利用请求url
        if(requestUrl.indexOf(QUESTION_MARK) != -1){
            requestUrl = requestUrl.substring(0, requestUrl.indexOf(QUESTION_MARK));
        }
        return resourceMap.get(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
*/
