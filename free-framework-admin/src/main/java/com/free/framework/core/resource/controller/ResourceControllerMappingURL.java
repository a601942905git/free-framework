package com.free.framework.core.resource.controller;

/**
 * 请求映射路径
 *
 */
public interface ResourceControllerMappingURL {

	String RESOURCE_CONTROLLER = "/resources";

	/**
	* 根路径请求映射路径,用于restful接口
	*/
	String RESOURCE = "/";

	/**
	* 详情请求映射路径
	*/
	String ONE_RESOURCE = "/{id}";

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
	String PAGE_LIST_RETURN = "resource/resource_list";

	/**
	* 详情页面返回路径
	*/
	String PAGE_DETAIL_RETURN = "resource/resource_detail";

	/**
	* 新增页面返回路径
	*/
	String PAGE_ADD_RETURN = "resource/resource_add";

	/**
	* 修改页面返回路径
	*/
	String PAGE_UPDATE_RETURN = "resource/resource_update";
}
