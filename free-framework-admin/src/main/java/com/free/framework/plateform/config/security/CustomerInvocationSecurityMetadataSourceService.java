package com.free.framework.plateform.config.security;

import com.free.framework.core.resource.entity.Resource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * com.free.framework.plateform.config.security.CustomerInvocationSecurityMetadataSourceService
 *
 * @author lipeng
 * @dateTime 2017/12/11 14:20
 */
@Component
public class CustomerInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    /**
     * 加载权限表中所有权限
     */
    public Map<String, Collection<ConfigAttribute>> loadResourceDefine(){
        HashMap<String, Collection<ConfigAttribute>> map = new HashMap<>(16);
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(new Resource(10001, "查询组织列表", -1, 1, "/organizations/", "1"));
        resourceList.add(new Resource(10001, "新增组织信息", -1, 2, "/organizations/page/add", "1"));
        resourceList.add(new Resource(10001, "修改组织信息", -1, 3, "/organizations/page/update", "1"));
        for(Resource resource : resourceList) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(resource.getName());
            // 此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            // 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(resource.getPath(), array);
        }
        return map;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Map<String, Collection<ConfigAttribute>> map = loadResourceDefine();
        // object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
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
