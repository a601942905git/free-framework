package com.free.framework.core.role.controller;

/**
 * com.free.framework.core.role.controller.RoleControllerMappingURL
 * 角色控制器url映射
 * @author lipeng
 * @dateTime 2017/9/9 23:05
 */
public interface RoleControllerMappingURL {

    String ROLE_CONTROLLER = "/roles";

    /**
     * 根路径请求映射路径,用于restful接口
     */
    String ROLE = "/";

    /**
     * 详情请求映射路径
     */
    String ONE_ROLE = "/{id}";

    /**
     * 新增请求映射路径
     */
    String PAGE_ADD = "/page/add";

    /**
     * 修改请求应映射路径
     */
    String PAGE_UPDATE = "/page/update";

    /**
     * 列表页返回路径
     */
    String PAGE_LIST_RETURN = "role/role_list";

    /**
     * 详情页面返回路径
     */
    String PAGE_DETAIL_RETURN = "role/role_detail";

    /**
     * 新增页面返回路径
     */
    String PAGE_ADD_RETURN = "role/role_add";

    /**
     * 修改页面返回路径
     */
    String PAGE_UPDATE_RETURN = "role/role_update";
}
