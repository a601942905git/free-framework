package com.free.framework.plateform.config.security;

import com.free.framework.core.resource.controller.param.ResourceParam;
import com.free.framework.core.resource.entity.Resource;
import com.free.framework.core.resource.service.ResourceService;
import com.free.framework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * com.free.framework.plateform.config.security.CustomerInvocationSecurityMetadataSourceService
 *
 * @author lipeng
 * @dateTime 2017/12/11 14:20
 */
@Component
public class CustomerInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private ResourceService resourceService;

    /**
     * 加载权限表中所有权限
     */
    public void  loadResourceDefine(){
        resourceMap = new HashMap<>();
        // 得所有角色并且得到这些角色各自拥有什么功能权限
        List<Resource> resourceList = resourceService.pageResource(new ResourceParam()).getList();

        if (CollectionUtils.isNotEmpty(resourceList)) {
            for (Resource resource : resourceList) {
                // 先检查该角色是否已加入过权限列表，如果是，只需要读取出来，然后继续添加功能权限；如果否，新增该角色的权限列表
                Collection<ConfigAttribute> configAttributes = resourceMap.get(resource.getPath());
                if(configAttributes == null){
                    configAttributes = new ArrayList<>();
                }

                ConfigAttribute configAttribute = new SecurityConfig(resource.getName());
                configAttributes.add(configAttribute);

                resourceMap.put(resource.getPath(), configAttributes);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        loadResourceDefine();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 判断请求是否有明文参数，如果是，截取参数前的url，不是则直接利用请求url
        if(requestUrl.indexOf("?") != -1){
            requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
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
