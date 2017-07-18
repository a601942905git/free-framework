package com.free.framework.core.user.controller;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface UserControllerMappingURL {

    String USER_CONTROLLER = "/users";

    /**
     * 根路径请求映射路径,用于restful接口
     */
    String USER = "/";

    /**
     * 详情请求映射路径
     */
    String ONE_USER = "/{id}";

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
    String PAGE_LIST_RETURN = "user/user_list";

    /**
     * 详情页面返回路径
     */
    String PAGE_DETAIL_RETURN = "user/user_detail";

    /**
     * 新增页面返回路径
     */
    String PAGE_ADD_RETURN = "user/user_add";

    /**
     * 修改页面返回路径
     */
    String PAGE_UPDATE_RETURN = "user/user_update";
}
